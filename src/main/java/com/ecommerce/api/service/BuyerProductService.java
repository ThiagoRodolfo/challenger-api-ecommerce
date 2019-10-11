package com.ecommerce.api.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecommerce.api.model.commons.FeeMonth;
import com.ecommerce.api.model.commons.PaymentCondition;
import com.ecommerce.api.model.commons.Product;
import com.ecommerce.api.model.request.BuyerRequest;
import com.ecommerce.api.model.response.InstallmentResponse;

@Service
public class BuyerProductService {

	static final Integer QTD_INSTALLMENTS = 6;

	static final Double FEE_SELIC = 1.15;

	public List<InstallmentResponse> validateBuyerProduct(BuyerRequest buyerRequest, FeeMonth feeMonth) {
		Integer numberInstallments = buyerRequest.getPaymentCondition().getNumberInstallments();

		List<InstallmentResponse> installments = new ArrayList<InstallmentResponse>();
		InstallmentResponse installment = null;

		AtomicLong numberGenerator = new AtomicLong(1l);

		if (numberInstallments <= QTD_INSTALLMENTS) {

			BigDecimal valueInstallments = this.calcValueInstallments(buyerRequest.getProduct().getValue(),
					buyerRequest.getPaymentCondition());

			for (int i = 0; i < numberInstallments; i++) {
				installment = new InstallmentResponse();
				Long id = numberGenerator.getAndIncrement();
				installment.setNumberInstallments(id.intValue());
				installment.setValue(valueInstallments);

				installments.add(installment);
			}
			
			
			this.calcIntegrityAmountProduct(installments, buyerRequest);

		} else {

			BigDecimal valueInstallments = this.calcValueInstallments(buyerRequest.getProduct().getValue(),
					buyerRequest.getPaymentCondition());

			for (int i = 0; i < numberInstallments; i++) {
				installment = new InstallmentResponse();
				Long id = numberGenerator.getAndIncrement();
				installment.setNumberInstallments(id.intValue());
				installment.setValue(this.calcValueInterest(valueInstallments, feeMonth.getFee()));

				installments.add(installment);
			}

		}
		
	

		return installments;
	}
	
	public List<InstallmentResponse>  calcIntegrityAmountProduct(List<InstallmentResponse> installments,BuyerRequest buyerRequest ) { 
		BigDecimal dif = BigDecimal.ZERO;
		Double sumInstallments  = installments.stream().map(i -> i.getValue()).mapToDouble(BigDecimal::doubleValue).sum();
		//cálculo pra manter a integridade do valor das parcelas com o total ofertado, caso o valor da soma das parcelas seja diferente do total do produto.
		if(buyerRequest.getProduct().getValue().compareTo(new BigDecimal(sumInstallments)) == 1 ) {
			 dif = buyerRequest.getProduct().getValue().subtract(new BigDecimal(sumInstallments)).setScale(4, RoundingMode.UP);
			 InstallmentResponse installmentResponse = installments.get(installments.size()-1);
			 installmentResponse.setValue(installmentResponse.getValue().add(dif));	
		}
		
		return installments;
	
		
	}
	

	public BigDecimal calcValueInterest(BigDecimal valueInstallments, BigDecimal interestFee) {
		BigDecimal interestInsuredAmount = interestFee.multiply(valueInstallments).divide(new BigDecimal(100));
		MathContext m = new MathContext(2);
		valueInstallments = valueInstallments.add(interestInsuredAmount);

		return valueInstallments.setScale(2, RoundingMode.DOWN);
	}
	public BigDecimal calcValueInstallments(BigDecimal amount, PaymentCondition paymentCondition) {

		if (paymentCondition.getInputValue().compareTo(BigDecimal.ZERO) == 1) {
			amount = amount.subtract(paymentCondition.getInputValue());
		}

		BigDecimal valueInstallments = amount.divide(new BigDecimal(paymentCondition.getNumberInstallments()), 4,
				RoundingMode.HALF_EVEN);

		return valueInstallments;
	}

	public void generateIdProduct(BuyerRequest buyerRequest) {
		String uniqueID = UUID.randomUUID().toString();
		Product product = new Product();

		if (!StringUtils.isEmpty(buyerRequest.getProduct())) {
			product = buyerRequest.getProduct();
			product.setCodigo(uniqueID);
		}

	}

}

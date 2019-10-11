package com.ecommerce.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecommerce.api.model.commons.FeeMonth;
import com.ecommerce.api.model.commons.PaymentCondition;
import com.ecommerce.api.model.commons.Product;
import com.ecommerce.api.model.request.BuyerRequest;
import com.ecommerce.api.model.response.InstallmentResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuyerProductServiceTest {

	public BuyerProductService service;

	@Before
	public void init() {
		this.service = new BuyerProductService();
	}

	@Test
	public void validaResultadoCalculoDeParcelas() {

		BigDecimal expected = new BigDecimal(250).setScale( 4, RoundingMode.HALF_EVEN);

		BigDecimal result = service.calcValueInstallments(new BigDecimal(1000.00), createPaymentCondition(4));

		assertEquals(expected.toString(), result.toString());

	}

	@Test
	public void validaCalculoDeTaxasDasParcelas() {
		
		
		BigDecimal expected = new BigDecimal(100.46).setScale(2,RoundingMode.UP);
		
		BigDecimal result = service.calcValueInterest(new BigDecimal(100.00), new BigDecimal(0.46));
		
		assertEquals(expected.toString(), result.toString());
				
	}
	
	@Test
	public void validaCompraProdutoComQquantidadeParcelasMaiorQueSeis(){ 
		
		List<InstallmentResponse> response = new ArrayList<InstallmentResponse>(); 
	
		response = service.validateBuyerProduct(createBuyerRequest(7), createFeeMonth());
		
		assertEquals(response.size(), 7);
	
	}
	
	
	@Test
	public void validaCompraProdutoComQquantidadeParcelasMenorQueSeis(){ 
		
		List<InstallmentResponse> response = new ArrayList<InstallmentResponse>(); 
	
		response = service.validateBuyerProduct(createBuyerRequest(5), createFeeMonth());
		
		assertEquals(response.size(), 5);
	
	}
	
	@Test
	public void validaGeracaoDeIdProdutoNull() {
		BuyerRequest buyerRequest = new BuyerRequest();
		service.generateIdProduct(buyerRequest);
		
		assertNull(buyerRequest.getProduct());

	}
	
	
	@Test
	public void validaGeracaoDeIdProdutoNotNull() {
		BuyerRequest buyerRequest = new BuyerRequest();
		buyerRequest.setProduct(new Product());
		service.generateIdProduct(buyerRequest);
		
		assertNotNull(buyerRequest.getProduct());
		
		assertNotNull(buyerRequest.getProduct().getCodigo());
	}
	
	
	private Product createProduct() {
		Product product = new Product();
		product.setCodigo("123456789");
		product.setName("Produto1");
		product.setValue(new BigDecimal(2000.00));

		return product;
	}

	private PaymentCondition createPaymentCondition(Integer value) {
		PaymentCondition paymentCondition = new PaymentCondition();
		paymentCondition.setInputValue(BigDecimal.ZERO);
		paymentCondition.setNumberInstallments(value);

		return paymentCondition;
	}
	
	private BuyerRequest createBuyerRequest(Integer value) {
		BuyerRequest buyerRequest = new BuyerRequest();
		buyerRequest.setProduct(createProduct());
		buyerRequest.setPaymentCondition(createPaymentCondition(value));

		return buyerRequest;
	}
	
	private FeeMonth createFeeMonth() {
		FeeMonth feeMonth = new FeeMonth();
		feeMonth.setFee(new BigDecimal(1.05));
		feeMonth.setMes(9);
		return feeMonth;
	}
	


}

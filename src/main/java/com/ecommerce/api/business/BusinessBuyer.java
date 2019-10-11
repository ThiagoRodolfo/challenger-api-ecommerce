package com.ecommerce.api.business;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ecommerce.api.model.commons.FeeMonth;
import com.ecommerce.api.model.commons.Selic;
import com.ecommerce.api.model.request.BuyerRequest;
import com.ecommerce.api.model.response.InstallmentResponse;
import com.ecommerce.api.service.BuyerProductRestService;
import com.ecommerce.api.service.BuyerProductService;

@Component
public class BusinessBuyer {

	@Autowired
	private BuyerProductRestService rest;

	@Autowired
	private BuyerProductService service;

	public ResponseEntity<?> managerBuyerProduct(BuyerRequest buyerRequest, HttpServletRequest request) {

		try {

			service.generateIdProduct(buyerRequest);

			List<Selic> selicResponse = getSelicBcb();

			FeeMonth feeMonth = consolidateFeeLastMonth(selicResponse);

			List<InstallmentResponse> installments = service.validateBuyerProduct(buyerRequest, feeMonth);

			if (!installments.isEmpty()) {

				return new ResponseEntity<Object>(installments, HttpStatus.CREATED);
			}

			return new ResponseEntity<Object>(installments, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public FeeMonth consolidateFeeLastMonth(List<Selic> selicResponse) {

		FeeMonth feeMonth = new FeeMonth();
		LocalDate now = LocalDate.now();
		int currentYear = now.getYear();
		int previousMonth = now.getMonthValue() - 1;

		BigDecimal sum = BigDecimal.ZERO;

		for (Selic selic : selicResponse) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dt = LocalDate.parse(selic.getData(), formatter);

			if (dt.getYear() == currentYear) {
				if (dt.getMonthValue() == previousMonth) {
					feeMonth.setMes(previousMonth);
					sum = sum.add(selic.getValor());
				}
			}

		}

		feeMonth.setFee(sum);

		return feeMonth;
	}

	public List<Selic> getSelicBcb() {
		List<Selic> response = null;

		try {
			response = rest.getFeeSelic();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;

	}

}

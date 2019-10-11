package com.ecommerce.api.business;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecommerce.api.model.commons.FeeMonth;
import com.ecommerce.api.model.commons.Selic;
import com.ecommerce.api.model.request.BuyerRequest;
import com.ecommerce.api.model.response.InstallmentResponse;
import com.ecommerce.api.service.BuyerProductRestService;
import com.ecommerce.api.service.BuyerProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessBuyerTest {

	@InjectMocks
	BusinessBuyer service;
	@Mock
	BuyerProductService productService;
	@Mock
	BuyerProductRestService productRestService;

	@Test
	public void ValidaCompraDeProduto() throws IOException {

		
		List<InstallmentResponse> responseActual = new ArrayList<InstallmentResponse>(); 
		responseActual.add(new  InstallmentResponse());
		Mockito.doNothing().when(productService).generateIdProduct(Mockito.any(BuyerRequest.class));

		Mockito.when(productRestService.getFeeSelic()).thenReturn(GetSelicResult());
		
		Mockito.when(productService.validateBuyerProduct(Mockito.any(BuyerRequest.class),Mockito.any(FeeMonth.class))).thenReturn(responseActual);
		
		
		ResponseEntity<?> response = service.managerBuyerProduct(new BuyerRequest(), null);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	private List<Selic> GetSelicResult() {

		List<Selic> selics = new ArrayList<Selic>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (int i = 0; i < 2; i++) {
			selics.add(new Selic(LocalDate.now().plusMonths(i).format(formatter), new BigDecimal(i)));
		}

		return selics;

	}

}

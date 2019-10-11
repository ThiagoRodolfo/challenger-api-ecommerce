package com.ecommerce.api.model.request;

import com.ecommerce.api.model.commons.PaymentCondition;
import com.ecommerce.api.model.commons.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerRequest {

	@JsonProperty("product")
	private Product product;

	@JsonProperty("paymentCondition")
	private PaymentCondition paymentCondition;

}

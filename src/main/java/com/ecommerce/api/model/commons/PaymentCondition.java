package com.ecommerce.api.model.commons;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCondition {

	@JsonProperty("inputValue")
	private BigDecimal inputValue;

	@JsonProperty("numberInstallments")
	private Integer numberInstallments;


}

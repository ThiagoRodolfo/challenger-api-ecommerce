package com.ecommerce.api.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentResponse {

	@JsonProperty("numberInstallments")
	public Integer numberInstallments;

	@JsonProperty("value")
	public BigDecimal value;
	
	@JsonProperty("feeInterestRate")
	public String feeInterestRate;


}

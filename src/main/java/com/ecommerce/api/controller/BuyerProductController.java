package com.ecommerce.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.business.BusinessBuyer;
import com.ecommerce.api.model.commons.Selic;
import com.ecommerce.api.model.request.BuyerRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/buyer")	
public class BuyerProductController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	BusinessBuyer businessBuyer;

	@PostMapping("/product")
	@ApiOperation(value = "buy product", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Selic.class),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server") })
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BuyerRequest data, HttpServletRequest request) throws Exception {

		try {

			ResponseEntity<?> result = businessBuyer.managerBuyerProduct(data, request);
			return result;

		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

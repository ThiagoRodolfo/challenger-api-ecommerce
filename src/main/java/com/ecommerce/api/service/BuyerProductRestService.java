package com.ecommerce.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ecommerce.api.model.commons.Selic;
import com.ecommerce.api.utils.Constants;
import com.ecommerce.api.utils.RestUtils;
import com.google.gson.Gson;

@Service
public class BuyerProductRestService {

	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

	Gson gson = new Gson();

	public List<Selic> getFeeSelic() throws IOException {

		String response = RestUtils.makeRequest(null, Constants.URL_SEARCH_SELIC, HttpMethod.GET, String.class);

		Selic[] selic = gson.fromJson(response, Selic[].class);

		List<Selic> fees = new ArrayList<Selic>();

		fees = Arrays.asList(selic);

		return fees;
	}
	
	

}

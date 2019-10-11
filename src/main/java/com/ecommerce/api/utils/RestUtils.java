package com.ecommerce.api.utils;

import java.io.IOException;

import org.springframework.http.HttpMethod;

import com.ecommerce.api.factory.RestTemplateFactory;

public class RestUtils {

	public static <T, R> T makeRequest(R requestBody, String url, HttpMethod httpMethod, Class<T> clazz)
			throws IOException {
		return new RestTemplateFactory().makeRequest(requestBody, url, httpMethod, clazz);
	}
	
}

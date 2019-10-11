package com.ecommerce.api.factory;

import java.io.IOException;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestTemplateFactory {

	public RestTemplate getObject() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}

	public <T, R> T makeRequest(R requestBody, String url, HttpMethod httpMethod, Class<T> clazz) throws IOException {

		HttpEntity<?> request = new HttpEntity<Object>(requestBody);

		try {

			ResponseEntity<T> response = getObject().exchange(url, httpMethod, request, clazz);

			return response.getBody();

		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
				return null;
			return new ObjectMapper().readValue(e.getResponseBodyAsString(), clazz);
		}

	}

}

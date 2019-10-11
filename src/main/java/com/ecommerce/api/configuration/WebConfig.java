package com.ecommerce.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer  {

	
	// Configuração de da rota de acesso ao swagger no Spring.
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder() {

			@Override
			public void configure(ObjectMapper objectMapper) {
				super.configure(objectMapper);
				objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
				objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.NONE);
				objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.PUBLIC_ONLY);
				objectMapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.PUBLIC_ONLY);

			}

		};
	}

}
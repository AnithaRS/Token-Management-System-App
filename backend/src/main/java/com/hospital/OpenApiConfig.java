package com.hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI tokenManagementAPI() {
		return new OpenAPI().info(new Info().title("Token management synstem API").version("1.0")
				.description("prodection style token and queue management API"));
	}
	
}

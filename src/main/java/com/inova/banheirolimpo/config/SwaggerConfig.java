package com.inova.banheirolimpo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
 
	@Bean
	public Docket detalheApi() {
 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.inova.banheirolimpo.resource"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build())
		.securitySchemes(Arrays.asList(apiKey()));
 
		return docket;
	}
	
	@Bean
	SecurityConfiguration security() {
	    return new SecurityConfiguration(null, null, null, null,"  ", ApiKeyVehicle.HEADER, "Authorization", ",");
	}
 
	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("BanheiroLimpo-Service");
		apiInfoBuilder.description("Serviço da aplicação BanheiroLimpo.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	private Contact contato() {
 
		return new Contact(
				"Markus Souza",
				null, 
				"markus.souza@gmail.com");
	}
	
	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}
}

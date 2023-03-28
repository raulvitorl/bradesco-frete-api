package br.com.bradesco.frete.api.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo("Bradesco Frete API", "Api responsável por recuperar calcular fretes", "0.0.3", "Terms of service",
          new Contact("Raul Vitor Lopes da Costa", "semurl.com.br", "raulawliet@gmail.com"),
          "Sem Licença Atualmente", "www.semlicenca.com.br", Collections.emptyList());
    }
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build();
	}	
}
package br.com.bradesco.frete.api.exception.handler;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import br.com.bradesco.frete.api.exception.FeignCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

	private static final String CEP_NAO_ENCONTRADO = "Não foi encontrado na api viacep nenhuma equivalencia para o cep informado.";

	@Override
	public Exception decode(String methodKey, Response response) {
		
		String rawResponse =getRawExceptionText(response); 
		if(response.status()==400){
			return new FeignCustomException(HttpStatus.NOT_FOUND.value(),CEP_NAO_ENCONTRADO);
		}
		return new FeignCustomException(response.status(),rawResponse);
	}

	private String getRawExceptionText(Response response) {
		try {
			final var body = response.body();
			StringWriter writer = new StringWriter();
			IOUtils.copy(body.asInputStream(), writer, "UTF-8");
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

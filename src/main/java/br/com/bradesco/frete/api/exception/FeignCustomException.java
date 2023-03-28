package br.com.bradesco.frete.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.FeignException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cep nao encontrado")
public class FeignCustomException extends FeignException {

	private static final long serialVersionUID = 4708869922532299246L;

	public FeignCustomException(int code, String message) {
        super(code, message);
    }
}


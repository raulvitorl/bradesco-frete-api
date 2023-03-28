package br.com.bradesco.frete.api.usecase;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;

public interface BuscarCepUseCase {

    public ResponseEntity<?> execute(ConsultaCep consultaCep,HttpServletRequest request);

}

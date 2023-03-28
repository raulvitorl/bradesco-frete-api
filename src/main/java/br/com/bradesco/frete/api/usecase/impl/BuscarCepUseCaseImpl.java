package br.com.bradesco.frete.api.usecase.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;
import br.com.bradesco.frete.api.integration.CepIntegration;
import br.com.bradesco.frete.api.usecase.BuscarCepUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscarCepUseCaseImpl implements BuscarCepUseCase{
	
	
	
	private final CepIntegration cepIntegration;
	
	@Override
	public ResponseEntity<?> execute(ConsultaCep consultaCep) {
		return new ResponseEntity<>(cepIntegration.getCep(consultaCep.getCep()), HttpStatus.OK);
	}
	
	
}

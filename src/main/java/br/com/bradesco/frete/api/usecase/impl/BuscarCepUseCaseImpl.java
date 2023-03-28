package br.com.bradesco.frete.api.usecase.impl;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;
import br.com.bradesco.frete.api.controller.dto.RetornoCep;
import br.com.bradesco.frete.api.exception.ApiErrorResponse;
import br.com.bradesco.frete.api.exception.FeignCustomException;
import br.com.bradesco.frete.api.helpers.RegiaoHelper;
import br.com.bradesco.frete.api.integration.CepIntegration;
import br.com.bradesco.frete.api.integration.resource.CepResource;
import br.com.bradesco.frete.api.usecase.BuscarCepUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscarCepUseCaseImpl implements BuscarCepUseCase{
	
	
	
	private final CepIntegration cepIntegration;
	
	@Override
	public ResponseEntity<?> execute(ConsultaCep consultaCep,HttpServletRequest request) {
		if(consultaCep.getCep()==null){
			String errorMessage = "Cep não informado na requesição.";
			HttpStatus errorStatus = HttpStatus.PRECONDITION_FAILED;
			log.error(errorMessage);
			return new ResponseEntity<>(
					ApiErrorResponse.builder()
					.createdAt(LocalDateTime.now())
					.message(errorMessage)
					.path(request.getRequestURL().toString())
					.status(errorStatus.value())
					.build(),
					errorStatus); 
		}
		String cepTexto = consultaCep.getCep().replaceAll( "[^\\d]", "" );
		CepResource response = new CepResource();
		try {
			response = cepIntegration.getCep(consultaCep.getCep());
		} catch (FeignCustomException fce) {
			String errorMessage = fce.getMessage();
			HttpStatus errorStatus = HttpStatus.valueOf(fce.status());
			log.error(errorMessage);
			return new ResponseEntity<>(
					ApiErrorResponse.builder()
					.createdAt(LocalDateTime.now())
					.message(errorMessage)
					.path(request.getRequestURL().toString())
					.status(errorStatus.value())
					.build(),
					errorStatus);
		}
		
		RetornoCep cepMapeado = montaRespostaFrete(response,cepTexto);
		return new ResponseEntity<>(cepMapeado, HttpStatus.OK);
	}

	private RetornoCep montaRespostaFrete(CepResource response,String cepTexto) {
		Double freteFixo = 0.0;
		log.info("Iniciando verificacao de região com base no range do cep.");
		Integer cepNumerico = Integer.valueOf(cepTexto);
		String regiao = "";
		if(RegiaoHelper.isSudeste(cepNumerico)){
			regiao = "SUDESTE";
			freteFixo = 7.85;
		}else if(RegiaoHelper.isNordeste(cepNumerico)){
			regiao = "NORDESTE";
			freteFixo = 15.98;
		}else if(RegiaoHelper.isSul(cepNumerico)){
			regiao = "SUL";
			freteFixo = 17.30;
		}else if(RegiaoHelper.isNorte(cepNumerico)){
			regiao = "NORTE";
			freteFixo = 20.83;
		}else{
			regiao = "CENTRO-OESTE";
			freteFixo = 12.50;
		}
		log.info("CEP ["+cepTexto+"] percencente a ["+regiao+"]");
		return RetornoCep.builder()
				.bairro(response.getBairro())
				.cep(response.getCep())
				.cidade(response.getLocalidade())
				.complemento(response.getComplemento())
				.estado(response.getUf())
				.rua(response.getLogradouro())
				.frete(freteFixo)
				.build();
	}
	
	
}

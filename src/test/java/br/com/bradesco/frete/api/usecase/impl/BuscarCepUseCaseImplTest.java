package br.com.bradesco.frete.api.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;
import br.com.bradesco.frete.api.controller.dto.RetornoCep;
import br.com.bradesco.frete.api.exception.ApiErrorResponse;
import br.com.bradesco.frete.api.exception.FeignCustomException;
import br.com.bradesco.frete.api.integration.CepIntegration;
import br.com.bradesco.frete.api.integration.resource.CepResource;

@ContextConfiguration(classes = {BuscarCepUseCaseImpl.class})
@ExtendWith(SpringExtension.class)
class BuscarCepUseCaseImplTest {
	@Autowired
	private BuscarCepUseCaseImpl buscarCepUseCaseImpl;

	@MockBean
	private CepIntegration cepIntegration;

	private static final String CEP_NAO_ENCONTRADO = "Não foi encontrado na api viacep nenhuma equivalencia para o cep informado.";

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepVazioEntaoPrecondicaoFalhou() {
	String cep1 = "";
		ConsultaCep consultaCep = new ConsultaCep(cep1);

		ResponseEntity<?> actualExecuteResult = buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());
		boolean expectedHasBodyResult = true;
		assertEquals(expectedHasBodyResult, actualExecuteResult.hasBody());
		boolean expectedIsEmptyResult = true;
		assertEquals(expectedIsEmptyResult, actualExecuteResult.getHeaders().isEmpty());
		HttpStatus expectedStatusCode = HttpStatus.PRECONDITION_FAILED;
		assertEquals(expectedStatusCode, actualExecuteResult.getStatusCode());
		String expectedMessage = "Cep não informado na requesição.";
		assertEquals(expectedMessage, ((ApiErrorResponse) actualExecuteResult.getBody()).getMessage());
		Object expectedError = null;
		assertEquals(expectedError, ((ApiErrorResponse) actualExecuteResult.getBody()).getError());
		String expectedPath = "http://localhost";
		assertEquals(expectedPath, ((ApiErrorResponse) actualExecuteResult.getBody()).getPath());
		int expectedStatus = 412;
		assertEquals(expectedStatus, ((ApiErrorResponse) actualExecuteResult.getBody()).getStatus());
	}

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepNuloEntaoPrecondicaoFalhou() {
		String cep1 = null;
		ConsultaCep consultaCep = new ConsultaCep(cep1);

		ResponseEntity<?> actualExecuteResult = buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());

		boolean expectedHasBodyResult = true;
		assertEquals(expectedHasBodyResult, actualExecuteResult.hasBody());
		boolean expectedIsEmptyResult = true;
		assertEquals(expectedIsEmptyResult, actualExecuteResult.getHeaders().isEmpty());
		HttpStatus expectedStatusCode = HttpStatus.PRECONDITION_FAILED;
		assertEquals(expectedStatusCode, actualExecuteResult.getStatusCode());
		String expectedMessage = "Cep não informado na requesição.";
		assertEquals(expectedMessage, ((ApiErrorResponse) actualExecuteResult.getBody()).getMessage());
		Object expectedError = null;
		assertEquals(expectedError, ((ApiErrorResponse) actualExecuteResult.getBody()).getError());
		String expectedPath = "http://localhost";
		assertEquals(expectedPath, ((ApiErrorResponse) actualExecuteResult.getBody()).getPath());
		int expectedStatus = 412;
		assertEquals(expectedStatus, ((ApiErrorResponse) actualExecuteResult.getBody()).getStatus());
	}

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepValidoSulEntaoOk() {
		when(cepIntegration.getCep((String) any())).thenReturn(new CepResource());
		String cep = "80000000";
		ConsultaCep consultaCep = new ConsultaCep(cep);
		ResponseEntity<?> actualExecuteResult = buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());
		boolean expectedHasBodyResult = true;
		assertEquals(expectedHasBodyResult, actualExecuteResult.hasBody());
		boolean expectedIsEmptyResult = true;
		assertEquals(expectedIsEmptyResult, actualExecuteResult.getHeaders().isEmpty());
		HttpStatus expectedStatusCode = HttpStatus.OK;
		assertEquals(expectedStatusCode, actualExecuteResult.getStatusCode());
		double expectedDoubleValueResult = 17.3d;
		assertEquals(expectedDoubleValueResult, ((RetornoCep) actualExecuteResult.getBody()).getFrete().doubleValue());
		verify(cepIntegration).getCep((String) any());
	}

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepValidoNorteEntaoOk() {
		when(cepIntegration.getCep((String) any())).thenReturn(new CepResource());
		String cep = "69911032";
		ConsultaCep consultaCep = new ConsultaCep(cep);
		ResponseEntity<?> actualExecuteResult = buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());
		boolean expectedHasBodyResult = true;
		assertEquals(expectedHasBodyResult, actualExecuteResult.hasBody());
		boolean expectedIsEmptyResult = true;
		assertEquals(expectedIsEmptyResult, actualExecuteResult.getHeaders().isEmpty());
		HttpStatus expectedStatusCode = HttpStatus.OK;
		assertEquals(expectedStatusCode, actualExecuteResult.getStatusCode());
		double expectedDoubleValueResult = 20.83d;
		assertEquals(expectedDoubleValueResult, ((RetornoCep) actualExecuteResult.getBody()).getFrete().doubleValue());
		verify(cepIntegration).getCep((String) any());
	}

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepValidoNordesteEntaoOk() {
		when(cepIntegration.getCep((String) any())).thenReturn(new CepResource());
		String cep = "40000000";
		ConsultaCep consultaCep = new ConsultaCep(cep);
		ResponseEntity<?> actualExecuteResult = buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());
		boolean expectedHasBodyResult = true;
		assertEquals(expectedHasBodyResult, actualExecuteResult.hasBody());
		boolean expectedIsEmptyResult = true;
		assertEquals(expectedIsEmptyResult, actualExecuteResult.getHeaders().isEmpty());
		HttpStatus expectedStatusCode = HttpStatus.OK;
		assertEquals(expectedStatusCode, actualExecuteResult.getStatusCode());
		double expectedDoubleValueResult = 15.98d;
		assertEquals(expectedDoubleValueResult, ((RetornoCep) actualExecuteResult.getBody()).getFrete().doubleValue());
		verify(cepIntegration).getCep((String) any());
	}

	/**
	 * Method under test: {@link BuscarCepUseCaseImpl#execute(ConsultaCep, HttpServletRequest)}
	 */
	@Test
	void quandoCepNaoEncontradoDeveRetornarNaoEncontrado() {
		int code = 404;
		String message = CEP_NAO_ENCONTRADO;
		when(cepIntegration.getCep((String) any())).thenThrow(new FeignCustomException(code, message));
		String cep = "123456";
		ConsultaCep consultaCep = new ConsultaCep(cep);
		buscarCepUseCaseImpl.execute(consultaCep, new MockHttpServletRequest());
	}

}


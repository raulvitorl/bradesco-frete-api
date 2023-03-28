package br.com.bradesco.frete.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;
import br.com.bradesco.frete.api.integration.CepIntegration;
import br.com.bradesco.frete.api.usecase.BuscarCepUseCase;

@ContextConfiguration(classes = {FreteController.class})
@ExtendWith(SpringExtension.class)
class FreteControllerTest {
    @MockBean
    private BuscarCepUseCase buscarCepUseCase;

    @MockBean
    private CepIntegration cepIntegration;

    @Autowired
    private FreteController freteController;

    /**
     * Method under test: {@link FreteController#calcularFretePorCep(ConsultaCep, HttpServletRequest)}
     */
    @Test
    void testCalcularFretePorCep() throws Exception {
        // Arrange
        HttpStatus status = HttpStatus.OK;
        when(buscarCepUseCase.execute((ConsultaCep) any(), (HttpServletRequest) any()))
                .thenReturn(new ResponseEntity<>(status));

        ConsultaCep consultaCep = new ConsultaCep();
        String cep = "Cep";
        consultaCep.setCep(cep);
        String content = (new ObjectMapper()).writeValueAsString(consultaCep);
        String urlTemplate = "/frete/v1/consulta-endereco";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(freteController)
                .build()
                .perform(requestBuilder);

        // Assert
        int status1 = 200;
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(status1));
    }

}


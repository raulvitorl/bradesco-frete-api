package br.com.bradesco.frete.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bradesco.frete.api.controller.dto.ConsultaCep;
import br.com.bradesco.frete.api.usecase.BuscarCepUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("frete")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FreteController {
	
    private final BuscarCepUseCase buscarCepUseCase;

    @PostMapping("/v1/consulta-endereco")
    public ResponseEntity<?> calcularFretePorCep(@RequestBody ConsultaCep consultaCep) {
        return buscarCepUseCase.execute(consultaCep);
    }

 
}

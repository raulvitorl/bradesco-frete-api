package br.com.bradesco.frete.api.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.bradesco.frete.api.integration.resource.CepResource;

@FeignClient(name = "cep", url = "${cep.api.base.url}")   
public interface CepIntegration {

    @GetMapping("ws/{cep}/json/")
    public CepResource getCep(@PathVariable String cep); 
   
    
}

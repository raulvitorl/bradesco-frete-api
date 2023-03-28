package br.com.bradesco.frete.api.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ConsultaCep implements Serializable{

	private static final long serialVersionUID = -7064737942160432930L;
	
	private String cep;

}

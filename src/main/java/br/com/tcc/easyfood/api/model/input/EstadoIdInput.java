package br.com.tcc.easyfood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdInput {
	
	@NotNull
	private Long id;
	
}

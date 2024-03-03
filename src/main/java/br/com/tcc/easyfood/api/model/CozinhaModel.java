package br.com.tcc.easyfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.tcc.easyfood.api.model.view.EstabelecimentoView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {
	
	@JsonView(EstabelecimentoModel.class)
	private Long id;
	
	@JsonView(EstabelecimentoModel.class)
	private String categoriaCozinha;

}

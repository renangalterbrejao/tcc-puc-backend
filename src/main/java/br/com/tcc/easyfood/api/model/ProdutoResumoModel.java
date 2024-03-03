package br.com.tcc.easyfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoResumoModel {
	
	private Long id;
	private String categoriaProduto;
	private String tipoEmbalagem;
	private String quantidadeMedida;
	private String tipoMedida;

}

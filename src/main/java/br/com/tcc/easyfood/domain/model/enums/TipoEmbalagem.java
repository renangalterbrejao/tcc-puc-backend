package br.com.tcc.easyfood.domain.model.enums;

public enum TipoEmbalagem {

	BANDEJA("Bandeja"),
	CAIXA("Caixa"),
	CARTELA("Cartela"),
	EMBALAGEM("Embalagem"),
	GARRAFA("Garrafa"),
	LATA("Lata"),
	PACOTE("Pacote"),
	POTE("Pote"),
	SACHE("Sachê");
	
	private String descricao;
	
	TipoEmbalagem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

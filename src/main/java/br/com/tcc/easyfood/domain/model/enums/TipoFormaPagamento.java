package br.com.tcc.easyfood.domain.model.enums;

public enum TipoFormaPagamento {

	CARTAO_CREDITO("Cartão de Crédito"),
	PIX("Pix");
	
	private String descricao;
	
	TipoFormaPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

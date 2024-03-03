package br.com.tcc.easyfood.domain.model.enums;

public enum BandeiraCartao {

	AMERICAN_EXPRESS("American Express"),
	DINERS_CLUB("Diners Club"),
	HIPER_CARD("Hiper Card"),
	MASTERCARD("Mastercard"),
	VISA("Visa");
	
	private String descricao;
	
	BandeiraCartao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

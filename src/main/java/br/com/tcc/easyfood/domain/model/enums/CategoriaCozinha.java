package br.com.tcc.easyfood.domain.model.enums;

public enum CategoriaCozinha {

	ALEMA("Alemã"),
	AMERICANA("Americana"),
	ARABE("Arabe"),
	ARGENTINA("Argentina"),
	BRASILEIRA("Brasileira"),
	CHINESA("Chinesa"),
	ESPANHOLA("Espanhola"),
	FRANCESA("Francesa"),
	INDIANA("Indiana"),
	ITALIANA("Italiana"),
	JAPONESA("Japonesa"),
	MEXICANA("Mexicana"),
	MINEIRA("Mineira"),
	NORDESTINA("Nordestina"),
	OCIDENTAL("Ocidental"),
	ORIENTAL("Oriental"),
	SAUDAVEL("Saudável"),
	TAILANDESA("Tailandesa");
	
	private String descricao;
	
	CategoriaCozinha(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

package br.com.tcc.easyfood.domain.model.enums;

public enum CategoriaEstabelecimento {

	ADEGA("Adega"),
	BISTRO("Bistro"),
	BOLARIA("Bolaria"),
	BUFFET("Buffet"),
	CAFETERIA("Cafeteria"),
	CASA_CARNES("Casa de carnes"),
	CHURRASCARIA("Churrascaria"),
	CONVENIENCIA("Conveniência"),
	CREPERIA("Creperia"),
	CROISSANTERIA("Croissanteria"),
	DISTRIBUIDORA_BEBIDAS("Distribuidora de bebidas"),
	DOCERIA("Doceria"),
	FABRICA("Fábrica"),
	FOOD_TRUCK("Food truck"),
	HAMBURGUERIA("Hamburgueria"),
	HIDROPONIA("Hidroponia"),
	LANCHONETE("Lanchonete"),
	LOJA_ALIMENTOS("Loja de alimentos"),
	MERCADO("Mercado"),
	PADARIA("Padaria"),
	PAMONHARIA("Pamonharia"),
	PASTELARIA("Pastelaria"),
	PEIXARIA("Peixaria"),
	PIZZARIA("Pizzaria"),
	PUB("Pub"),
	RESTAURANTE("Estabelecimento"),
	ROTISSERIA("Roticeria"),
	SORVETERIA("Sorveteria"),
	TAPIOCARIA("Tapiocaria"),
	TEMAKERIA("Temakeria");
	
	private String descricao;
	
	CategoriaEstabelecimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

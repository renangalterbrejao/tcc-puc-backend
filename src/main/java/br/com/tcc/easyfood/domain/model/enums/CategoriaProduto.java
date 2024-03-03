package br.com.tcc.easyfood.domain.model.enums;

public enum CategoriaProduto {

	BEBIDA("Bebidas"),
	BISCOITO("Biscoitos"),
	BOLO("Bolos"),
	CARNE("Carnes"),
	CONGELADO("Congelados"),
	CONSERVA("Conservas"),
	DOCE("Doces"),
	FARINHA_CEREAL("Farinhas e Cereais"),
	FRIO("Frios"),
	FRUTA("Frutas"),
	LANCHE("Lanches"),
	LEGUME_VERDURA_GRAO("Legumes, verduras e grãos"),
	MANTEIGA_OLEO_CIA("Manteigas, óleos e cia"),
	MASSA("Massas"),
	MOLHO("Molhos"),
	OUTRO("Outros"),
	OVO_LEITE_QUEIJO_CIA("Ovos, leites, queijos e cia"),
	PAO("Pães"),
	PETISCO("Petiscos"),
	SALGADO("Salgados"),
	SOPA("Sopas"),
	SORVETE("Sorvetes");
	
	private String descricao;
	
	CategoriaProduto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

package br.com.tcc.easyfood.domain.model.enums;

public enum TipoMedida {

	UNIDADE("Unidade"),
	MILILITRO("Mililitro"),
	LITRO("Litro"),
	MILIGRAMA("Miligrama"),
	GRAMA("Grama"),
	KILOGRAMA("Kilograma");
	
	private String descricao;
	
	TipoMedida(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}

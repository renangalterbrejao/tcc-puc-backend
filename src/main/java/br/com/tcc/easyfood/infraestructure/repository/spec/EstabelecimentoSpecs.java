package br.com.tcc.easyfood.infraestructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.tcc.easyfood.domain.model.Estabelecimento;

public class EstabelecimentoSpecs {
	
	public static Specification<Estabelecimento> comFreteGratis() {
		
		return (root, query, builder) -> 
		builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Estabelecimento> comNomeSemelhante(String nome) {

		return (root, query, builder) -> 
		builder.like(root.get("nome"), "%" + nome + "%");
	}
}

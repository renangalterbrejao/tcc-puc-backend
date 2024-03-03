package br.com.tcc.easyfood.infraestructure.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tcc.easyfood.domain.model.Estabelecimento;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EstabelecimentoComNomeSemelhanteSpec implements Specification<Estabelecimento> {

	private static final long serialVersionUID = 1L;
	
	private String nome;

	@Override
	public Predicate toPredicate(Root<Estabelecimento> root, CriteriaQuery<?> query, 
			CriteriaBuilder builder) {
		
		return builder.like(root.get("nome"), "%" + nome + "%");
	}

}

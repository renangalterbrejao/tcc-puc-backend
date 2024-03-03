package br.com.tcc.easyfood.infraestructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoRepository;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoRepositoryQueries;
import br.com.tcc.easyfood.infraestructure.repository.spec.EstabelecimentoSpecs;

@Repository
public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Override
	public List<Estabelecimento> find(
			String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Estabelecimento> criteria = builder.createQuery(Estabelecimento.class);
		
		Root<Estabelecimento> root = criteria.from(Estabelecimento.class); // from Estabelecimento
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if(taxaFreteInicial != null) {
			predicates.add(builder
					.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		
		if(taxaFreteFinal != null) {
			predicates.add(builder
					.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Estabelecimento> query = manager.createQuery(criteria);
		
		return query.getResultList();
		
		/*var jpql = new StringBuilder();
		var parametros = new HashMap<String, Object>();
		
		jpql.append("FROM Estabelecimento WHERE 0 = 0 ");
		
		if(StringUtils.hasLength(nome)) {
			jpql.append("AND nome LIKE :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if(taxaFreteInicial != null) {
			jpql.append("AND taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		
		if(taxaFreteFinal != null) {
			jpql.append("AND taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Estabelecimento> query = manager.
				createQuery(jpql.toString(), Estabelecimento.class);
				
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		return query.getResultList();*/
		
		/*return manager.createQuery(jpql.toString(), Estabelecimento.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaInicial", taxaFreteInicial)
				.setParameter("taxaFinal", taxaFreteFinal)
				.getResultList();*/
		
	}

	@Override
	public List<Estabelecimento> findComFreteGratis(String nome) {
		
		return estabelecimentoRepository.findAll(EstabelecimentoSpecs.comFreteGratis()
				.and(EstabelecimentoSpecs.comNomeSemelhante(nome)));
	}
	
}

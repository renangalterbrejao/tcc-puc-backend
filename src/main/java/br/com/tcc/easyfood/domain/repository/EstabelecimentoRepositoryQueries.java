package br.com.tcc.easyfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.tcc.easyfood.domain.model.Estabelecimento;

public interface EstabelecimentoRepositoryQueries {

	List<Estabelecimento> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Estabelecimento> findComFreteGratis(String nome);

}
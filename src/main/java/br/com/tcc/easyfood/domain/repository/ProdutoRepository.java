package br.com.tcc.easyfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Produto;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>  {	
	
	//@Query(value= "SELECT distinct (prod.categoria_produto) from produto prod", nativeQuery = true)
	List<Produto> findAll();
	
}

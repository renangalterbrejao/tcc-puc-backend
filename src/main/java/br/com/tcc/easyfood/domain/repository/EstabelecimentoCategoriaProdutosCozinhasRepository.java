package br.com.tcc.easyfood.domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.api.model.EstabelecimentoCategoriaProdutosResumoModel;

@Repository
public interface EstabelecimentoCategoriaProdutosCozinhasRepository 
	extends CustomJpaRepository<EstabelecimentoCategoriaProdutosResumoModel, Long>, JpaSpecificationExecutor<EstabelecimentoCategoriaProdutosResumoModel> {
	
	@Query(value = "SELECT DISTINCT(P.categoria_produto) AS categoria, E.id AS id_estabelecimento "
			+ "FROM estabelecimento E "
			+ "INNER JOIN estabelecimento_produto EP ON E.id = EP.estabelecimento_id "
			+ "INNER JOIN produto P ON EP.produto_id = P.id "
			+ "WHERE E.endereco_cidade_id = :cidadeId", nativeQuery = true)
	List<Map<EstabelecimentoCategoriaProdutosResumoModel, Object>> findAllCategoriaProdutosByCidadeId(Long cidadeId);
	
	@Query(value = "SELECT DISTINCT(C.categoria_cozinha) AS categoria, E.id AS id_estabelecimento "
			+ "FROM estabelecimento E "
			+ "INNER JOIN estabelecimento_cozinha EC ON E.id = EC.estabelecimento_id  "
			+ "INNER JOIN cozinha C ON EC.cozinha_id = C.id  "
			+ "WHERE E.endereco_cidade_id = :cidadeId", nativeQuery = true)
	List<Map<EstabelecimentoCategoriaProdutosResumoModel, Object>> findAllCategoriaCozinhasByCidadeId(Long cidadeId);
	
}

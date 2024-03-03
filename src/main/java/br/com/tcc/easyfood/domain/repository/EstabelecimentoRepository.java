package br.com.tcc.easyfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.api.model.EstabelecimentoCategoriaProdutosResumoModel;
import br.com.tcc.easyfood.domain.model.Estabelecimento;

@Repository
public interface EstabelecimentoRepository 
	extends CustomJpaRepository<Estabelecimento, Long>, EstabelecimentoRepositoryQueries, 
	JpaSpecificationExecutor<Estabelecimento> {
	
	@Query("from Estabelecimento r join fetch r.horario h join fetch r.usuario u ")
	List<Estabelecimento> findAll();
	
	@Query("from Estabelecimento r join fetch r.horario h join fetch r.usuario u where r.endereco.cidade.id = :cidadeId")
	List<Estabelecimento> findAllByCidadeId(Long cidadeId);
	
	@Query("from Estabelecimento r join fetch r.horario h join fetch r.formasPagamento f join fetch r.usuario u left join fetch r.cozinhas "
			+ " where r.id = :id")
	Estabelecimento findPorId(Long id);
	
	@Query("from Estabelecimento r join fetch r.horario h join fetch r.usuario u where r.nome like %:nome%")
	List<Estabelecimento> consultarPorNome(String nome);
	
}

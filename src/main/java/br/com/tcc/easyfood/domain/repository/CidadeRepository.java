package br.com.tcc.easyfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
	@Query("from Cidade c join fetch c.estado")
	List<Cidade> findAll();
	
	@Query("from Cidade c join fetch c.estado where c.nome like %:nome%")
	List<Cidade> consultarPorNome(String nome);
	
	@Query("from Cidade where nome like %:nome% and estado.id = :id")
	Cidade consultarPorNomeEstadoId(String nome, @Param("id") Long estadoId);
	
}

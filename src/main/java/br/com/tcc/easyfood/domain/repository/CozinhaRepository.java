package br.com.tcc.easyfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>  {	
	
	List<Cozinha> findTodasBycategoriaCozinhaContaining(String nome);
	
	List<Cozinha> findByCategoriaCozinha(String categoriaCozinha);
	
	boolean existsByCategoriaCozinha(String nome);
	
}

package br.com.tcc.easyfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Cliente;

@Repository
public interface ClienteRepository extends CustomJpaRepository<Cliente, Long> {

	
	
}

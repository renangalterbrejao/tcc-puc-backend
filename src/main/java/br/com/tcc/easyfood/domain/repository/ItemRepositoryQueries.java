package br.com.tcc.easyfood.domain.repository;

import br.com.tcc.easyfood.domain.model.FotoItem;

public interface ItemRepositoryQueries {

	FotoItem save(FotoItem foto);
	
	void delete(FotoItem foto);
	
}
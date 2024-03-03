package br.com.tcc.easyfood.infraestructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.model.FotoItem;
import br.com.tcc.easyfood.domain.repository.ItemRepositoryQueries;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoItem save(FotoItem foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void delete(FotoItem foto) {
		manager.remove(foto);
	}
	
}

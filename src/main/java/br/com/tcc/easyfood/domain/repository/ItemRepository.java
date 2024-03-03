package br.com.tcc.easyfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.model.FotoItem;
import br.com.tcc.easyfood.domain.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryQueries {

    @Query("from Item where estabelecimento.id = :estabelecimento and id = :item")
    Optional<Item> findById(@Param("estabelecimento") Long estabelecimentoId, 
            @Param("item") Long itemId);
    
    @Query("from Item i join fetch i.produto where i.estabelecimento.id = :estabelecimento")
    List<Item> findTodosByEstabelecimento(@Param("estabelecimento") Long estabelecimentoId);
    
    @Query("from Item i left join fetch i.produto where i.estabelecimento.id = :estabelecimento and i.ativo = true")
    List<Item> findAtivosByEstabelecimento(@Param("estabelecimento") Long estabelecimentoId);
    
    @Query("select f from FotoItem f join f.item p "
			+ "where p.estabelecimento.id = :estabelecimentoId and f.item.id = :itemId")
	Optional<FotoItem> findFotoById(Long estabelecimentoId, Long itemId);
    
}

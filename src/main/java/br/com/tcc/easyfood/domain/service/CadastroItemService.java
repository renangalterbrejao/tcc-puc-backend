package br.com.tcc.easyfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.ItemNaoEncontradoException;
import br.com.tcc.easyfood.domain.model.FotoItem;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.repository.ItemRepository;

@Service
public class CadastroItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Transactional
    public Item salvar(Item item) {
        return itemRepository.save(item);
    }
    
    public Item buscarOuFalhar(Long estabelecimentoId, Long itemId) {
        return itemRepository.findById(estabelecimentoId, itemId)
            .orElseThrow(() -> new ItemNaoEncontradoException(estabelecimentoId, itemId));
    }   
}

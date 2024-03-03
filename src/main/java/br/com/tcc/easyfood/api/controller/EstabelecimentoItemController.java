package br.com.tcc.easyfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.ItemInputDisassembler;
import br.com.tcc.easyfood.api.assembler.ItemModelAssembler;
import br.com.tcc.easyfood.api.model.ItemModel;
import br.com.tcc.easyfood.api.model.input.ItemInput;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.repository.ItemRepository;
import br.com.tcc.easyfood.domain.service.CadastroItemService;
import br.com.tcc.easyfood.domain.service.CadastroEstabelecimentoService;

@RestController
@RequestMapping("/estabelecimentos/{estabelecimentoId}/itens")
public class EstabelecimentoItemController {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private CadastroItemService cadastroItem;
    
    @Autowired
    private CadastroEstabelecimentoService cadastroEstabelecimento;
    
    @Autowired
    private ItemModelAssembler itemModelAssembler;
    
    @Autowired
    private ItemInputDisassembler itemInputDisassembler;
    
    @GetMapping
    public List<ItemModel> listar(@PathVariable Long estabelecimentoId, 
    		@RequestParam(required = false) boolean incluirInativos ) {
    	
        Estabelecimento estabelecimento = cadastroEstabelecimento.buscarOuFalhar(estabelecimentoId);
        List<Item> todosItens = null;
        
        if(incluirInativos) {
        	todosItens = itemRepository.findTodosByEstabelecimento(estabelecimento.getId());
        } else {
        	todosItens = itemRepository.findAtivosByEstabelecimento(estabelecimento.getId());
        }
        
        return itemModelAssembler.toCollectionModel(todosItens);
    }
    
    @GetMapping("/{itemId}")
    public ItemModel buscar(@PathVariable Long estabelecimentoId, @PathVariable Long itemId) {
        Item item = cadastroItem.buscarOuFalhar(estabelecimentoId, itemId);
        
        return itemModelAssembler.toModel(item);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemModel adicionar(@PathVariable Long estabelecimentoId,
            @RequestBody @Valid ItemInput itemInput) {
        Estabelecimento estabelecimento = cadastroEstabelecimento.buscarOuFalhar(estabelecimentoId);
        
        Item item = itemInputDisassembler.toDomainObject(itemInput);
        item.setEstabelecimento(estabelecimento);
        
        item = cadastroItem.salvar(item);
        
        return null;
    }
    
    @PutMapping("/{itemId}")
    public ItemModel atualizar(@PathVariable Long estabelecimentoId, @PathVariable Long itemId,
            @RequestBody @Valid ItemInput itemInput) {
        Item itemAtual = cadastroItem.buscarOuFalhar(estabelecimentoId, itemId);
        
        itemInputDisassembler.copyToDomainObject(itemInput, itemAtual);
        
        itemAtual = cadastroItem.salvar(itemAtual);
        
        return itemModelAssembler.toModel(itemAtual);
    }   
}

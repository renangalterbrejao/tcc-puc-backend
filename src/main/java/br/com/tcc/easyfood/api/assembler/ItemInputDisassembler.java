package br.com.tcc.easyfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.input.ItemInput;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.model.Produto;

@Component
public class ItemInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Item toDomainObject(ItemInput itemInput) {
        return modelMapper.map(itemInput, Item.class);
    }
    
    public void copyToDomainObject(ItemInput itemInput, Item item) {
        modelMapper.map(itemInput, item);
    }   
}

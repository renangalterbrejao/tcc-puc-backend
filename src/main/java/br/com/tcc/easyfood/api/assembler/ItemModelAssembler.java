package br.com.tcc.easyfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.ItemModel;
import br.com.tcc.easyfood.domain.model.Item;

@Component
public class ItemModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ItemModel toModel(Item item) {
        return modelMapper.map(item, ItemModel.class);
    }
    
    public List<ItemModel> toCollectionModel(List<Item> itens) {
        return itens.stream()
                .map(item -> toModel(item))
                .collect(Collectors.toList());
    }   
} 

package br.com.tcc.easyfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.ItemModel;
import br.com.tcc.easyfood.api.model.ItemPedidoModel;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.model.ItemPedido;

@Component
public class ItemPedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ItemPedidoModel toModel(ItemPedido itemPedido) {
        return modelMapper.map(itemPedido, ItemPedidoModel.class);
    }
    
    public List<ItemPedidoModel> toCollectionModel(List<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .map(itemPedido -> toModel(itemPedido))
                .collect(Collectors.toList());
    }   
} 

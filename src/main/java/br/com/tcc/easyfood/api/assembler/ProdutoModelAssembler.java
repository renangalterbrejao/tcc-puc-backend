package br.com.tcc.easyfood.api.assembler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.ProdutoResumoCategoriaModel;
import br.com.tcc.easyfood.api.model.ProdutoResumoModel;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ProdutoResumoCategoriaModel toResumoModel(String produto) {
        return modelMapper.map(produto, ProdutoResumoCategoriaModel.class);
    }
    
    public List<ProdutoResumoCategoriaModel> toCollectionResumoModel(Set<String> produtos) {
        return produtos.stream()
                .map(produto -> toResumoModel(produto))
                .collect(Collectors.toList());
    } 
} 

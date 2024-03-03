package br.com.tcc.easyfood.api.assembler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.CozinhaModel;
import br.com.tcc.easyfood.api.model.CozinhaResumoModel;
import br.com.tcc.easyfood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public CozinhaModel toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }
    
    public CozinhaResumoModel toResumoModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaResumoModel.class);
    }
    
    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    } 
    
    public List<CozinhaResumoModel> toCollectionResumoModel(Set<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toResumoModel(cozinha))
                .collect(Collectors.toList());
    } 
} 

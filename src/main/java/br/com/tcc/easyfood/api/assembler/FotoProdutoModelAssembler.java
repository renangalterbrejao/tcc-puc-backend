package br.com.tcc.easyfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.FotoProdutoModel;
import br.com.tcc.easyfood.domain.model.FotoItem;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoModel toModel(FotoItem foto) {
		return modelMapper.map(foto, FotoProdutoModel.class); 
	}
	
}

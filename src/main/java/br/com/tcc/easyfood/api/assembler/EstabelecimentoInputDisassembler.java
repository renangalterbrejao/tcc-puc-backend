package br.com.tcc.easyfood.api.assembler;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.input.EstabelecimentoInput;
import br.com.tcc.easyfood.domain.model.Cidade;
import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.model.Estabelecimento;

@Component
public class EstabelecimentoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

    public Estabelecimento toDomainObject(EstabelecimentoInput estabelecimentoInput) {
    	
    	return modelMapper.map(estabelecimentoInput, Estabelecimento.class);
    }
    
    public void copyToDomainObject(EstabelecimentoInput estabelecimentoInput, Estabelecimento estabelecimento) {
    	
    	// Para evitar org.hibernate.HibernateException: identifier of an instance of 
    	// br.com.tcc.easyfood.domain.model.Cozinha was altered from 1 to 2
    	Set<Cozinha> cozinhas = new HashSet<>();
    	cozinhas.add(new Cozinha());
    	estabelecimento.setCozinhas(cozinhas);
    	
    	if(estabelecimento.getEndereco() != null) {
    		estabelecimento.getEndereco().setCidade(new Cidade());
    	}
    	
    	modelMapper.map(estabelecimentoInput, estabelecimento);
    }
    
}

package br.com.tcc.easyfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.CozinhaModelAssembler;
import br.com.tcc.easyfood.api.model.CozinhaModel;
import br.com.tcc.easyfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas"
)
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@GetMapping 
	public List<CozinhaModel> listar() {
 
		return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll()); 
	}
	
}

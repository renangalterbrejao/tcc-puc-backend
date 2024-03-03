package br.com.tcc.easyfood.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.ProdutoModelAssembler;
import br.com.tcc.easyfood.api.model.ProdutoResumoCategoriaModel;
import br.com.tcc.easyfood.domain.model.Produto;
import br.com.tcc.easyfood.domain.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos"
)
public class ProdutoController {
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping 
	public List<ProdutoResumoCategoriaModel> listar() {
		
		List<Produto> produtos = produtoRepository.findAll();
		
		Set<String> produtosSet = new HashSet<String>(); 
		
		/* Itera sobre a lista de produtos retornada e salva em um set, para que na resposta não haja repetições*/
		for(int i=0; i<produtos.size(); i++) {
			produtosSet.add(produtos.get(i).getCategoriaProduto());
		}
		
		List<ProdutoResumoCategoriaModel> listaProdutosNaoRepetidos = new ArrayList<ProdutoResumoCategoriaModel>();
		
		/* Salva o set em um model de resposta da API */
		for (String categoriaProduto : produtosSet) {
		   
			ProdutoResumoCategoriaModel produtoResumoCategoriaModel = new ProdutoResumoCategoriaModel();
			produtoResumoCategoriaModel.setCategoriaProduto(categoriaProduto);
			
			listaProdutosNaoRepetidos.add(produtoResumoCategoriaModel);
		}
		
		return listaProdutosNaoRepetidos;
	}
	
}

package br.com.tcc.easyfood.api.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.CidadeInputDisassembler;
import br.com.tcc.easyfood.api.assembler.CidadeModelAssembler;
import br.com.tcc.easyfood.api.model.CidadeModel;
import br.com.tcc.easyfood.api.model.input.CidadeInput;
import br.com.tcc.easyfood.domain.exception.CidadeJaCadastradaException;
import br.com.tcc.easyfood.domain.exception.EstadoNaoEncontradoException;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.model.Cidade;
import br.com.tcc.easyfood.domain.repository.CidadeRepository;
import br.com.tcc.easyfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler; 
	
	@GetMapping
	public List<CidadeModel> listar() {
		
		List<Cidade> todasCidades = cidadeRepository.findAll();
		Collections.sort(todasCidades);
	    
	    return cidadeModelAssembler.toCollectionModel(todasCidades);
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
	    
	    return cidadeModelAssembler.toModel(cidade);
	}
	
	@GetMapping("porNome/{nomeCidade}")
	public List<CidadeModel> buscar(@PathVariable String nomeCidade) {
		
		List<Cidade> cidades = cadastroCidade.buscarPorNome(nomeCidade);
	    
	    return cidadeModelAssembler.toCollectionModel(cidades);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {	
		
		try {
			
			Cidade cidadeAtual = cadastroCidade.buscarPorNomeEstado(cidadeInput.getNome(), 
					cidadeInput.getEstado().getId());
			
			if(cidadeAtual != null) {
				throw new CidadeJaCadastradaException(cidadeAtual.getNome(), 
						cidadeAtual.getEstado().getNome());
			}
			
	        Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
	        
	        cidade = cadastroCidade.salvar(cidade);
	        
	        return cidadeModelAssembler.toModel(cidade);
	    } catch (EstadoNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    } catch (CidadeJaCadastradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(
			@RequestBody @Valid CidadeInput cidadeInput, @PathVariable Long cidadeId){
		
		try {
	        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
	        
	        cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
	        
	        cidadeAtual = cadastroCidade.salvar(cidadeAtual);
	        
	        return cidadeModelAssembler.toModel(cidadeAtual);
	    } catch (EstadoNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
	/*
	 * @DeleteMapping("/{cidadeId}")
	 * 
	 * @ResponseStatus(HttpStatus.NO_CONTENT) public void remover(@PathVariable Long
	 * cidadeId) {
	 * 
	 * cadastroCidade.excluir(cidadeId);
	 * 
	 * }
	 */
	
}

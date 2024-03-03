package br.com.tcc.easyfood.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.TypeKey;

import br.com.tcc.easyfood.api.assembler.CozinhaModelAssembler;
import br.com.tcc.easyfood.api.assembler.EstabelecimentoInputDisassembler;
import br.com.tcc.easyfood.api.assembler.EstabelecimentoModelAssembler;
import br.com.tcc.easyfood.api.assembler.ProdutoModelAssembler;
import br.com.tcc.easyfood.api.model.EstabelecimentoCategoriaProdutosResumoModel;
import br.com.tcc.easyfood.api.model.EstabelecimentoModel;
import br.com.tcc.easyfood.api.model.EstabelecimentoResumoModel;
import br.com.tcc.easyfood.api.model.input.EstabelecimentoInput;
import br.com.tcc.easyfood.domain.exception.CidadeNaoEncontradaException;
import br.com.tcc.easyfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.tcc.easyfood.domain.exception.EstabelecimentoNaoEncontradoException;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoCategoriaProdutosCozinhasRepository;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoRepository;
import br.com.tcc.easyfood.domain.service.CadastroEstabelecimentoService;

@RestController
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoController {
	
	@Autowired
	private CadastroEstabelecimentoService cadastroEstabelecimento;
	
	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private EstabelecimentoModelAssembler estabelecimentoModelAssembler;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private EstabelecimentoInputDisassembler estabelecimentoInputDisassembler;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	private EstabelecimentoCategoriaProdutosCozinhasRepository estabelecimentoCategoriaProdutosRepository;
	
	@GetMapping
	public List<EstabelecimentoResumoModel> listar() {
		return estabelecimentoModelAssembler.toCollectionResumoModel(estabelecimentoRepository.findAll());
	}
	
	@GetMapping("/por-cidade/{cidadeId}")
	public List<EstabelecimentoResumoModel> listarPorCidadeId(@PathVariable Long cidadeId) {
		
		
		List<EstabelecimentoResumoModel> estabelecimentosEncontrados = 
				estabelecimentoModelAssembler.toCollectionResumoModel(estabelecimentoRepository.findAllByCidadeId(cidadeId));
		
		List<EstabelecimentoResumoModel> estabelecimentosEncontradosCopia = new ArrayList<EstabelecimentoResumoModel>();
		estabelecimentosEncontradosCopia.addAll(estabelecimentosEncontrados);
		
		/* Preenche os possíveis tipos de produtos para todos os estabelecimentos */
		
		List<Map<EstabelecimentoCategoriaProdutosResumoModel, Object>> categoriasProdutos = 
				estabelecimentoCategoriaProdutosRepository.findAllCategoriaProdutosByCidadeId(cidadeId);
		
		for(int x=0; x<categoriasProdutos.size(); x++) {
			
			Map<EstabelecimentoCategoriaProdutosResumoModel, Object> objeto = 
					new HashMap<EstabelecimentoCategoriaProdutosResumoModel, Object>(categoriasProdutos.get(x)); 
			
			for(int j=0; j<estabelecimentosEncontradosCopia.size(); j++) {
				if(objeto.get("id_estabelecimento").toString().equals(estabelecimentosEncontradosCopia.get(j).getId().toString())) {
					estabelecimentosEncontrados.get(j).getCategoriasProdutos().add(objeto.get("categoria").toString());
				}
			}	
			
		}
		
		/* Preenche os possíveis tipos de cozinhas para todos os estabelecimentos */
		
		List<Map<EstabelecimentoCategoriaProdutosResumoModel, Object>> categoriasCozinhas = 
				estabelecimentoCategoriaProdutosRepository.findAllCategoriaCozinhasByCidadeId(cidadeId);
		
		for(int x=0; x<categoriasCozinhas.size(); x++) {
			
			Map<EstabelecimentoCategoriaProdutosResumoModel, Object> objeto = 
					new HashMap<EstabelecimentoCategoriaProdutosResumoModel, Object>(categoriasCozinhas.get(x)); 
			
			for(int j=0; j<estabelecimentosEncontradosCopia.size(); j++) {
				if(objeto.get("id_estabelecimento").toString().equals(estabelecimentosEncontradosCopia.get(j).getId().toString())) {
					estabelecimentosEncontrados.get(j).getCategoriasCozinhas().add(objeto.get("categoria").toString());
				}
			}	
			
		}
		
		
		return estabelecimentosEncontrados;
	}
	
	@GetMapping("/por-nome")
	public List<EstabelecimentoResumoModel> listarEstabelecimentosPorNome(
			String nome) {
		
		return estabelecimentoModelAssembler.toCollectionResumoModel(estabelecimentoRepository.consultarPorNome(nome));
	}
	
	@GetMapping("/{estabelecimentoId}")
	public EstabelecimentoModel buscar(@PathVariable Long estabelecimentoId) {
		
		/*Implementação para se usar o DTO de Estabelecimento*/
		Estabelecimento estabelecimento = cadastroEstabelecimento.buscarOuFalhar(estabelecimentoId);
		
		return estabelecimentoModelAssembler.toModel(estabelecimento);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstabelecimentoModel adicionar(@RequestBody @Valid EstabelecimentoInput estabelecimentoInput) {
	    try {
	        return estabelecimentoModelAssembler.toModel(cadastroEstabelecimento.salvar(estabelecimentoInputDisassembler.toDomainObject(estabelecimentoInput)));
	    } catch (CozinhaNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage());
	    }
	}
	
	@PutMapping("/{estabelecimentoId}")
	public EstabelecimentoModel alterar(
			@RequestBody @Valid EstabelecimentoInput estabelecimentoInput, @PathVariable Long estabelecimentoId) {
			
		try {
			
			Estabelecimento estabelecimentoAtual = cadastroEstabelecimento.buscarOuFalhar(estabelecimentoId);
			
			estabelecimentoInputDisassembler.copyToDomainObject(estabelecimentoInput, estabelecimentoAtual);
			
			return estabelecimentoModelAssembler.toModel(cadastroEstabelecimento.salvar(estabelecimentoAtual));
			
		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{estabelecimentoId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long estabelecimentoId) {
		cadastroEstabelecimento.ativar(estabelecimentoId);
	}
	
	@DeleteMapping("/{estabelecimentoId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long estabelecimentoId) {
		cadastroEstabelecimento.inativar(estabelecimentoId);
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> estabelecimentoIds) {
		try {
			cadastroEstabelecimento.ativar(estabelecimentoIds);
		} catch (EstabelecimentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> estabelecimentoIds) {
		try {
			cadastroEstabelecimento.inativar(estabelecimentoIds);
		} catch (EstabelecimentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	
	@PutMapping("/{estabelecimentoId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long estabelecimentoId) {
		cadastroEstabelecimento.abrir(estabelecimentoId);
	}

	@PutMapping("/{estabelecimentoId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long estabelecimentoId) {
		cadastroEstabelecimento.fechar(estabelecimentoId);
	}
	
}

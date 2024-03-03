package br.com.tcc.easyfood.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.tcc.easyfood.api.assembler.FotoProdutoModelAssembler;
import br.com.tcc.easyfood.api.model.FotoProdutoModel;
import br.com.tcc.easyfood.api.model.input.FotoProdutoInput;
import br.com.tcc.easyfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.tcc.easyfood.domain.model.FotoItem;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.service.CadastroItemService;
import br.com.tcc.easyfood.domain.service.CatalogoFotoItemService;
import br.com.tcc.easyfood.domain.service.FotoStorageService;
import br.com.tcc.easyfood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping("/estabelecimentos/{estabelecimentoId}/itens/{itemId}/foto")
public class EstabelecimentoItemFotoController {

	@Autowired
	private CadastroItemService cadastroItem;
	
	@Autowired
	private CatalogoFotoItemService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long estabelecimentoId,
			@PathVariable Long itemId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		Item item = cadastroItem.buscarOuFalhar(estabelecimentoId, itemId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoItem foto = new FotoItem();
		foto.setItem(item);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoItem fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscar(@PathVariable Long estabelecimentoId, 
			@PathVariable Long itemId) {
		FotoItem fotoItem = catalogoFotoProduto.buscarOuFalhar(estabelecimentoId, itemId);
		
		return fotoProdutoModelAssembler.toModel(fotoItem);
	}
	
	@GetMapping
	public ResponseEntity<?> servir(@PathVariable Long estabelecimentoId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
			FotoItem fotoItem = catalogoFotoProduto.buscarOuFalhar(estabelecimentoId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoItem.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoItem.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estabelecimentoId, 
	        @PathVariable Long produtoId) {
	    catalogoFotoProduto.excluir(estabelecimentoId, produtoId);
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}
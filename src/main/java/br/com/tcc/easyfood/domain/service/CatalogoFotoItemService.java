package br.com.tcc.easyfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.FotoProdutoNaoEncontradaException;
import br.com.tcc.easyfood.domain.model.FotoItem;
import br.com.tcc.easyfood.domain.repository.ItemRepository;
import br.com.tcc.easyfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoItem salvar(FotoItem foto, InputStream dadosArquivo) {
		
		Long estabelecimentoId = foto.getItem().getEstabelecimento().getId();
		Long itemId = foto.getItemId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoItem> fotoExistente = itemRepository
				.findFotoById(estabelecimentoId, itemId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			itemRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  itemRepository.save(foto);
		itemRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
		
		
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	@Transactional
	public void excluir(Long estabelecimentoId, Long itemId) {
	    FotoItem foto = buscarOuFalhar(estabelecimentoId, itemId);
	    
	    itemRepository.delete(foto);
	    itemRepository.flush();

	    fotoStorage.remover(foto.getNomeArquivo());
	}
	
	public FotoItem buscarOuFalhar(Long estabelecimentoId, Long itemId) {
	    return itemRepository.findFotoById(estabelecimentoId, itemId)
	            .orElseThrow(() -> new FotoProdutoNaoEncontradaException(estabelecimentoId, itemId));
	}
	
}
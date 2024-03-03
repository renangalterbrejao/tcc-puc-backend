package br.com.tcc.easyfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.easyfood.api.model.EstabelecimentoModel;
import br.com.tcc.easyfood.api.model.EstabelecimentoResumoModel;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.service.CadastroCidadeService;

@Component
public class EstabelecimentoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	public EstabelecimentoModel toModel(Estabelecimento estabelecimento) {
		
		return modelMapper.map(estabelecimento, EstabelecimentoModel.class);
	}
	
	public List<EstabelecimentoModel> toCollectionModel(List<Estabelecimento> estabelecimentos) {
		return estabelecimentos.stream()
				.map(estabelecimento -> toModel(estabelecimento))
				.collect(Collectors.toList());
	}
	
	public EstabelecimentoResumoModel toResumoModel(Estabelecimento estabelecimento) {
		
		return modelMapper.map(estabelecimento, EstabelecimentoResumoModel.class);
	}
	
	public List<EstabelecimentoResumoModel> toCollectionResumoModel(List<Estabelecimento> estabelecimentos) {
		return estabelecimentos.stream()
				.map(estabelecimento -> toResumoModel(estabelecimento))
				.collect(Collectors.toList());
	}
	
}

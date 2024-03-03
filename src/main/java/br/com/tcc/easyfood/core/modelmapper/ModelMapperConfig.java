package br.com.tcc.easyfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tcc.easyfood.api.model.EnderecoModel;
import br.com.tcc.easyfood.api.model.input.ItemPedidoInput;
import br.com.tcc.easyfood.domain.model.Endereco;
import br.com.tcc.easyfood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		//return new ModelMapper();
		
		var modelMapper = new ModelMapper();
		
		/*Incluido para se poder mapear o atributo TaxaFrete da classe de domínio Estabelecimento
		 * e relacionar com o atributo precoFrete na classe de Representation EstabelecimentoModel*/
		/*var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Estabelecimento.class, EstabelecimentoModel.class)
			.addMapping(Estabelecimento::getTaxaFrete, EstabelecimentoModel::setPrecoFrete);
		
		return modelMapper; */
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    .addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		return modelMapper;
		
	}
	
}

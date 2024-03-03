package br.com.tcc.easyfood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

import br.com.tcc.easyfood.api.assembler.ItemModelAssembler;
import br.com.tcc.easyfood.api.assembler.ItemPedidoModelAssembler;
import br.com.tcc.easyfood.api.assembler.PedidoInputDisassembler;
import br.com.tcc.easyfood.api.assembler.PedidoModelAssembler;
import br.com.tcc.easyfood.api.assembler.PedidoResumoModelAssembler;
import br.com.tcc.easyfood.api.model.ItemPedidoModel;
import br.com.tcc.easyfood.api.model.PedidoModel;
import br.com.tcc.easyfood.api.model.PedidoResumoModel;
import br.com.tcc.easyfood.api.model.input.PedidoInput;
import br.com.tcc.easyfood.core.data.PageableTranslator;
import br.com.tcc.easyfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.exception.PedidosPorUsuarioNaoEncontradosException;
import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.ItemPedido;
import br.com.tcc.easyfood.domain.model.Pedido;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.repository.PedidoRepository;
import br.com.tcc.easyfood.domain.service.CadastroUsuarioService;
import br.com.tcc.easyfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private ItemPedidoModelAssembler itemPedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired
    private CadastroUsuarioService cadastroUsuario;
	
	@GetMapping("porUsuario/{usuarioId}")
	public List<PedidoResumoModel> pesquisar(@PathVariable(required = true) Long usuarioId) {
		
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		List<Pedido> pedidosLista = pedidoRepository.findAllByUsuario(usuario.getId());
		
		if(pedidosLista.isEmpty()) {
			throw new PedidosPorUsuarioNaoEncontradosException(usuario.getNome());
		}
		
		List<Pedido> pedidos = pedidoRepository.findPedidosAllData(pedidosLista.stream().collect(Collectors.toList()));
		

		List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler
				.toCollectionModel(pedidos);

		return pedidosResumoModel;
	}

	@GetMapping("porCodigo/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}
	
	@GetMapping("itensporCodigo/{codigoPedido}")
	public List<ItemPedidoModel> buscarItensPorCodigo(@PathVariable String codigoPedido) {
		List<ItemPedido> listaItenspedido = emissaoPedido.buscarItensOuFalhar(codigoPedido);

		return itemPedidoModelAssembler.toCollectionModel(listaItenspedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

}

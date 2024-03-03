package br.com.tcc.easyfood.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.ItensNaoEncontradosException;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.exception.PedidoNaoEncontradoException;
import br.com.tcc.easyfood.domain.model.Cidade;
import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.model.FormaPagamento;
import br.com.tcc.easyfood.domain.model.Item;
import br.com.tcc.easyfood.domain.model.ItemPedido;
import br.com.tcc.easyfood.domain.model.Pedido;
import br.com.tcc.easyfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private CadastroEstabelecimentoService cadastroEstabelecimento;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroClienteService cadastroCliente;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;
    
    @Autowired
    private CadastroItemService cadastroItem;
    
    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.calcularFrete();
        pedido.calcularPrecoFinal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoPedido().getCidade().getId());
        Cliente cliente = (Cliente)cadastroCliente.buscarOuFalhar(pedido.getCliente().getId());
        Estabelecimento estabelecimento = cadastroEstabelecimento.buscarOuFalhar(pedido.getEstabelecimento().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoPedido().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setEstabelecimento(estabelecimento);
        pedido.setFormaPagamento(formaPagamento);
        
        if (estabelecimento.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse estabelecimento.",
                    formaPagamento.getTipoFormaPagamento()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(itemPedido -> {
            Item item = cadastroItem.buscarOuFalhar(
                    pedido.getEstabelecimento().getId(), itemPedido.getItem().getId());
            
            itemPedido.setPedido(pedido);
            itemPedido.setItem(item);
            itemPedido.setPrecoItemPedido(item.getPreco());
            itemPedido.setPrecoTotalItemPedido(itemPedido.calcularPrecoTotalItemPedido(itemPedido));
            itemPedido.setDescontoItemPedido(item.getDesconto());
            itemPedido.calcularDescontoTotalItemPedido(itemPedido);
       
        });
    }
    
    public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
			.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}            
    
    public List<ItemPedido> buscarItensOuFalhar(String codigoPedido) {
    	
    	List<ItemPedido> itens = new ArrayList<ItemPedido>();
    	List<Pedido> listaItensPedido = pedidoRepository.findItensPedidoByCodigo(codigoPedido);
    	
    	if(listaItensPedido.isEmpty()) {
    		throw new ItensNaoEncontradosException(codigoPedido);
    	}
    	
    	for(int i = 0; i<listaItensPedido.get(0).getItens().size(); i++) {
    		itens.add(listaItensPedido.get(0).getItens().get(i));
    	}
    	
    	return itens;
    	
	}
}

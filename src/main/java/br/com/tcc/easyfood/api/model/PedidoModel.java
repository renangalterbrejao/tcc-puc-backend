package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoModel {
    
    private String codigo;
	private BigDecimal taxaFrete;
    private BigDecimal subtotal;
    private BigDecimal descontoTotal;
    private BigDecimal precoFinal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private EstabelecimentoResumoModel estabelecimento;
    private FormaPagamentoModel formaPagamento;
    private String nomePedido;
    private String emailPedido;
    private String contatoPedido;
    private String cpfPedido;
    private EnderecoModel enderecoPedido;
    private List<ItemPedidoModel> itens;
    private String chavePixPedido;
    private String numeroCartaoPedido;
    private String dataValidadeCartaoPedido;
    private String codigoSegurancaCartaoPedido;
    private String bandeiraCartaoPedido;
    
}

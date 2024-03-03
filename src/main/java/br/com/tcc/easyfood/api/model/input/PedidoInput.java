package br.com.tcc.easyfood.api.model.input;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInput {
	
	@Valid
    @NotNull
    private UsuarioIdInput cliente;

    @Valid
    @NotNull
    private EstabelecimentoIdInput estabelecimento;
    
    @Valid
    @NotNull
    private String nomePedido;
    
    @Valid
    @NotNull
    private String emailPedido;
    
    @Valid
    @NotNull
    private String contatoPedido;
    
    @Valid
    @CPF
    @NotNull
    private String cpfPedido;
    
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;
    
    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;
    
    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;
    
    @Valid
    private String chavePixPedido;

    @Valid
    private String numeroCartaoPedido;
    
    @Valid
    private OffsetDateTime dataValidadeCartaoPedido;
    
    @Valid
    private String codigoSegurancaCartaoPedido;
    
    @Valid
    private String bandeiraCartaoPedido;
    
}

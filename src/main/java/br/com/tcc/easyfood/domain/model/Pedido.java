package br.com.tcc.easyfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.AbstractAggregateRoot;

import br.com.tcc.easyfood.domain.event.PedidoCanceladoEvent;
import br.com.tcc.easyfood.domain.event.PedidoConfirmadoEvent;
import br.com.tcc.easyfood.domain.event.PedidoContestadoEvent;
import br.com.tcc.easyfood.domain.event.PedidoFalhadoEvent;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.model.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido> {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(nullable = false)
	private String codigo;
	
	@JoinColumn(nullable = false)
    private BigDecimal taxaFrete;
	
	@JoinColumn(nullable = false)
	private BigDecimal subtotal;
	
	@JoinColumn(nullable = false)
    private BigDecimal descontoTotal;
	
	@JoinColumn(nullable = false)
    private BigDecimal precoFinal;
	
    private String status = StatusPedido.CRIADO.toString();
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioCriacao;
	
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioConfirmacao;
	
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioEntrega;
	
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioCancelamento;
	
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioFalha;
	
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataHorarioContestacao;
	
	@JoinColumn(nullable = true)
	private String motivoCancelamento;
	
	@JoinColumn(nullable = false)
	private String nomePedido;
	
	@JoinColumn(nullable = false)
	private String emailPedido;
	
	@JoinColumn(nullable = false)
	private String contatoPedido;
	
    @Embedded
    private Endereco enderecoPedido;
    
    @CPF
    @JoinColumn(nullable = false)
	private String cpfPedido;
    
    @JoinColumn(nullable = true)
	private String chavePixPedido;
    
    @JoinColumn(nullable = true)
	private String numeroCartaoPedido;
    
    @Column(nullable = true, columnDefinition = "dateTime")
	private OffsetDateTime dataValidadeCartaoPedido;
    
    @JoinColumn(nullable = true)
	private String codigoSegurancaCartaoPedido;
    
    @Column(nullable = true)
	private String bandeiraCartaoPedido;
    
    @UpdateTimestamp
	@Column(nullable = true, columnDefinition = "time")
	private OffsetTime tempoEntrega;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Estabelecimento estabelecimento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cliente_id", nullable = true)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();
    
    public BigDecimal calcularSubtotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotalItemPedido);
        
        return this.subtotal = getItens().stream()
            .map(item -> item.getPrecoTotalItemPedido())
            .reduce(BigDecimal.ZERO, BigDecimal::add).add(calcularFrete());
    }
    
    public BigDecimal calcularDescontoTotal() {
        getItens().forEach(ItemPedido::calcularDescontoTotalItemPedido);
        
        return this.descontoTotal = getItens().stream()
            .map(item -> item.getDescontoTotalItemPedido())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void calcularPrecoFinal() {
    	this.precoFinal = calcularSubtotal().subtract(calcularDescontoTotal());
    }
    
    //Calculo do valor do frete
    public BigDecimal calcularFrete() {
        setTaxaFrete(getEstabelecimento().getTaxaMinimaFrete());
        
        return getTaxaFrete();
    }
    
    //Calculo do tempo de entrega
    public void calcularTempoEntrega() {
        setTempoEntrega(OffsetTime.now().plusHours(Integer.valueOf(2)));
    }

    public void atribuirPedidoAosItens() {
        getItens().forEach(item -> item.setPedido(this));
    }
    
    public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO.toString());
		setDataHorarioConfirmacao(OffsetDateTime.now());
		
		registerEvent(new PedidoConfirmadoEvent(this));
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE.toString());
		setDataHorarioEntrega(OffsetDateTime.now());
	}
	
	public void cancelar() {
	    setStatus(StatusPedido.CANCELADO.toString());
	    setDataHorarioCancelamento(OffsetDateTime.now());
	    
	    registerEvent(new PedidoCanceladoEvent(this));
	}
	
	public void falhar() {
	    setStatus(StatusPedido.FALHADO.toString());
	    setDataHorarioFalha(OffsetDateTime.now());
	    
	    registerEvent(new PedidoFalhadoEvent(this));
	}
	
	public void contestar() {
	    setStatus(StatusPedido.CONTESTADO.toString());
	    setDataHorarioContestacao(OffsetDateTime.now());
	    
	    registerEvent(new PedidoContestadoEvent(this));
	}
	
	private void setStatus(String novoStatus) {
		
		if (StatusPedido.valueOf(getStatus()).naoPodeAlterarPara(StatusPedido.valueOf(novoStatus))) {
			throw new NegocioException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s",
							getCodigo(), getStatus(), 
							novoStatus));
		}
		
		this.status = novoStatus;
	}
	
	//Antes de persistir o pedido, execute este método
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}

}

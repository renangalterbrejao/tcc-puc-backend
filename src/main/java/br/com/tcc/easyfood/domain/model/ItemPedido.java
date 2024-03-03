package br.com.tcc.easyfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal precoItemPedido;
	
	@Column(nullable = false)
	private BigDecimal descontoItemPedido;
	
	@Column(nullable = false)
	private BigDecimal precoTotalItemPedido;
	
	@Column(nullable = false)
	private BigDecimal descontoTotalItemPedido;
	
	@Column(nullable = true)
	private String observacao;
	
	@ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Item item;
	
	public BigDecimal calcularPrecoTotalItemPedido(ItemPedido itemPedido) {
	    BigDecimal precoUnitario = itemPedido.getItem().getPreco();
	    Integer quantidade = itemPedido.getQuantidade();

	    if (precoUnitario == null) {
	        precoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    return precoUnitario.multiply(new BigDecimal(quantidade));
	}
	
	public BigDecimal calcularDescontoTotalItemPedido(ItemPedido itemPedido) {
	    BigDecimal descontoUnitario = itemPedido.getItem().getDesconto();
	    Integer quantidade = itemPedido.getQuantidade();

	    if (descontoUnitario == null) {
	    	descontoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    return descontoUnitario.multiply(new BigDecimal(quantidade));
	}
	
	public void calcularPrecoTotalItemPedido() {
	    BigDecimal precoUnitario = this.getPrecoItemPedido();
	    Integer quantidade = this.getQuantidade();

	    if (precoUnitario == null) {
	        precoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    this.setPrecoTotalItemPedido(precoUnitario.multiply(new BigDecimal(quantidade)));
	}
	
	public void calcularDescontoTotalItemPedido() {
	    BigDecimal descontoUnitario = this.getDescontoItemPedido();
	    Integer quantidade = this.getQuantidade();

	    if (descontoUnitario == null) {
	    	descontoUnitario = BigDecimal.ZERO;
	    }

	    if (quantidade == null) {
	        quantidade = 0;
	    }

	    this.setDescontoTotalItemPedido(descontoUnitario.multiply(new BigDecimal(quantidade)));
	}


}

package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

    private Long id;
    private ItemModel item;
    private Integer quantidade;
    private BigDecimal precoItemPedido;
    private BigDecimal descontoItemPedido;
    private BigDecimal precoTotalItemPedido;
    private BigDecimal descontoTotalItemPedido;
    private String observacao;            
}

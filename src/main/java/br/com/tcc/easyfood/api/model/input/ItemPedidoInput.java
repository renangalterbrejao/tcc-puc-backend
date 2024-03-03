package br.com.tcc.easyfood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoInput {

    @NotNull
    private Long produtoId;
    
    @NotNull
    @Positive
    private Integer quantidade;
    
    private String observacao;   
}

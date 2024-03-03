package br.com.tcc.easyfood.domain.event;

import br.com.tcc.easyfood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoContestadoEvent {

    private Pedido pedido;
    
}

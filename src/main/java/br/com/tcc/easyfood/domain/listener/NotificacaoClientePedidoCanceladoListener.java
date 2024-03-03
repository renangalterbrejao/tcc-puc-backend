package br.com.tcc.easyfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.tcc.easyfood.domain.event.PedidoCanceladoEvent;
import br.com.tcc.easyfood.domain.model.Pedido;
import br.com.tcc.easyfood.domain.service.EnvioEmailService;
import br.com.tcc.easyfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmail;
    
    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();
        
        var mensagem = Mensagem.builder()
                .assunto(pedido.getEstabelecimento().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getEmailPedido())
                .build();

        envioEmail.enviar(mensagem);
    }
    
}

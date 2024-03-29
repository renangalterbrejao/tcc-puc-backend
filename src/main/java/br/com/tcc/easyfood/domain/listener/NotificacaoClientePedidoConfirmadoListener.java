package br.com.tcc.easyfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.tcc.easyfood.domain.event.PedidoConfirmadoEvent;
import br.com.tcc.easyfood.domain.model.Pedido;
import br.com.tcc.easyfood.domain.service.EnvioEmailService;
import br.com.tcc.easyfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder().assunto(pedido.getEstabelecimento().getCategoria() + " " +pedido.getEstabelecimento().getNome()
				  + " - Pedido confirmado").corpo("O pedido de código <strong>" +
				  pedido.getCodigo() + "</strong> foi confirmado!")
				  .corpo("pedido-confirmado.html").variavel("pedido", pedido)
				  .destinatario(pedido.getEmailPedido()).build();

		envioEmail.enviar(mensagem);
	}
	
}
package br.com.tcc.easyfood.domain.exception;

public class PedidosPorUsuarioNaoEncontradosException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;
    
    public PedidosPorUsuarioNaoEncontradosException(String usuarioId) {
        super(String.format("Não existem pedidos para o(a) usuário(a) %s", usuarioId));
    }
}

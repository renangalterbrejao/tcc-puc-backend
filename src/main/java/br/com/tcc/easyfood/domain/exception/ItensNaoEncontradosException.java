package br.com.tcc.easyfood.domain.exception;

public class ItensNaoEncontradosException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ItensNaoEncontradosException(String codigoPedido) {
        super(String.format("Não existem itens para o pedido de código %s", 
        		codigoPedido));
    }
    
}

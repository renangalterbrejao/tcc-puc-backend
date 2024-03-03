package br.com.tcc.easyfood.domain.exception;

public class ItemNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public ItemNaoEncontradoException(Long estabelecimentoId, Long produtoId) {
        this(String.format("Não existe um cadastro de item com código %d para o estabelecimento de código %d", 
                produtoId, estabelecimentoId));
    }
    
}

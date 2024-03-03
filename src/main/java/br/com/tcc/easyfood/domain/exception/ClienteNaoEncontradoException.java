package br.com.tcc.easyfood.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public ClienteNaoEncontradoException(Long usuarioId) {
        this(String.format("Não existe um cadastro de cliente com código %d", usuarioId));
    }           
}

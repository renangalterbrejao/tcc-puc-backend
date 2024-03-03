package br.com.tcc.easyfood.domain.exception;

public class CidadeJaCadastradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public CidadeJaCadastradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeJaCadastradaException(String nomeCidade, String nomeEstado) {
		this(String.format("Já existe um cadastro de cidade com nome %s para o estado '%s'", nomeCidade, nomeEstado));
	}
	
}

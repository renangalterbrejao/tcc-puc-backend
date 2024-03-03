package br.com.tcc.easyfood.domain.exception;

public class EstabelecimentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public EstabelecimentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstabelecimentoNaoEncontradoException(Long estabelecimentoId) {
		this(String.format("Não existe um cadastro de estabelecimento com código %d", estabelecimentoId));
	}
	
}

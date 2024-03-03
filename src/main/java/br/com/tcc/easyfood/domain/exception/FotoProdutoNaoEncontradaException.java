package br.com.tcc.easyfood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public FotoProdutoNaoEncontradaException(Long estabelecimentoId, Long produtoId) {
		this(String.format("Não existe um cadastro de foto do produto com código %d para o estabelecimento de código %d",
				produtoId, estabelecimentoId));
	}

}
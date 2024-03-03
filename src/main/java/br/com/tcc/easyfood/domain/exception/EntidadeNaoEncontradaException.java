package br.com.tcc.easyfood.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)  //, reason = "Entidade não encontrada")
//public class EntidadeNaoEncontradaException extends ResponseStatusException {
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	/*public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}

	public EntidadeNaoEncontradaException(String mensagem) {
		this(HttpStatus.NOT_FOUND, mensagem);
	}*/
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}

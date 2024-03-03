package br.com.tcc.easyfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.EstabelecimentoNaoEncontradoException;
import br.com.tcc.easyfood.domain.model.Cidade;
import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.model.FormaPagamento;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoRepository;

@Service
public class CadastroEstabelecimentoService {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Transactional
	public Estabelecimento salvar(Estabelecimento estabelecimento) {
		//Long cozinhaId = estabelecimento.getCozinha().getId();
		Long cidadeId = estabelecimento.getEndereco().getCidade().getId();

		//Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		//estabelecimento.setCozinha(cozinha);
		estabelecimento.getEndereco().setCidade(cidade);

		return estabelecimentoRepository.save(estabelecimento);
	}
	
	@Transactional
	public void ativar(Long estabelecimentoId) {
		Estabelecimento estabelecimentoAtual = buscarOuFalhar(estabelecimentoId);
		
		estabelecimentoAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long estabelecimentoId) {
		Estabelecimento estabelecimentoAtual = buscarOuFalhar(estabelecimentoId);
		
		estabelecimentoAtual.inativar();
	}
	
	@Transactional
	public void ativar(List<Long> estabelecimentoIds) {
		
		estabelecimentoIds.forEach(this::ativar);
		
	}
	
	@Transactional
	public void inativar(List<Long> estabelecimentosIds) {
		
		estabelecimentosIds.forEach(this::inativar);
		
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long estabelecimentoId, Long formaPagamentoId) {
		Estabelecimento estabelecimento = buscarOuFalhar(estabelecimentoId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		
		estabelecimento.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long estabelecimentoId, Long formaPagamentoId) {
		Estabelecimento estabelecimento = buscarOuFalhar(estabelecimentoId);
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		
		estabelecimento.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void abrir(Long estabelecimentoId) {
	    Estabelecimento estabelecimentoAtual = buscarOuFalhar(estabelecimentoId);
	    
	    estabelecimentoAtual.abrir();
	}

	@Transactional
	public void fechar(Long estabelecimentoId) {
	    Estabelecimento estabelecimentoAtual = buscarOuFalhar(estabelecimentoId);
	    
	    estabelecimentoAtual.fechar();
	}

	public Estabelecimento buscarOuFalhar(Long estabelecimentoId) {
		
		Estabelecimento estabelecimento = estabelecimentoRepository.findPorId(estabelecimentoId);
		
		if(estabelecimento == null) {
			throw new EstabelecimentoNaoEncontradoException(estabelecimentoId);
		}
		
		return estabelecimento;
	}

}

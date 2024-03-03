package br.com.tcc.easyfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.FormaPagamentoModelAssembler;
import br.com.tcc.easyfood.api.model.FormaPagamentoModel;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.service.CadastroEstabelecimentoService;

@RestController
@RequestMapping(value = "/estabelecimentos/{estabelecimentoId}/formas-pagamento")
public class EstabelecimentoFormaPagamentoController {
	
	@Autowired
	private CadastroEstabelecimentoService cadastroEstabelecimento;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Long estabelecimentoId) {
		
		Estabelecimento estabelecimento = cadastroEstabelecimento.buscarOuFalhar(estabelecimentoId);
		
		return formaPagamentoModelAssembler.toCollectionModel(estabelecimento.getFormasPagamento());
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long estabelecimentoId, @PathVariable Long formaPagamentoId) {
		cadastroEstabelecimento.desassociarFormaPagamento(estabelecimentoId, formaPagamentoId);
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long estabelecimentoId, @PathVariable Long formaPagamentoId) {
		cadastroEstabelecimento.associarFormaPagamento(estabelecimentoId, formaPagamentoId);
	}
	
}

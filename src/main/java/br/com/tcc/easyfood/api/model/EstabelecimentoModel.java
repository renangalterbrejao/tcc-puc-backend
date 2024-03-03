package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.tcc.easyfood.api.model.view.EstabelecimentoView;
import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.model.FormaPagamento;
import br.com.tcc.easyfood.domain.model.Horario;
import br.com.tcc.easyfood.domain.model.Produto;
import br.com.tcc.easyfood.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstabelecimentoModel {
	
	private Long id;
	private String nome;
	private BigDecimal taxaMinimaFrete;
	private String categoria;
	private Boolean ativo;
	private Boolean aberto;
	private Boolean permitirAberturaAutomatica;
	private String cnpj;
	private Double notaSatisfacao;
	private String descricaoEmpresa;
	private Set<FormaPagamento> formasPagamento;
	private List<CozinhaResumoModel> cozinhas;
	private List<ProdutoResumoModel> produtos;
	private Horario horario;
	private Usuario usuario;

}

package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstabelecimentoResumoModel {

    private Long id;
    private String nome;  
    private String categoria;
    private Double notaSatisfacao;
    private BigDecimal taxaMinimaFrete;
    private List<String> categoriasCozinhas = new ArrayList<String>();
	private List<String> categoriasProdutos = new ArrayList<String>();
}

package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;

import br.com.tcc.easyfood.domain.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemModel {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal desconto;
    private Boolean ativo;
    private CozinhaResumoModel cozinha;
    private ProdutoResumoModel produto;
}

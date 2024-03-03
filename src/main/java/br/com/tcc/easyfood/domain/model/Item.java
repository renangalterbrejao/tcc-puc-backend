package br.com.tcc.easyfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Item {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private BigDecimal desconto;
	
	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;
	
	@JoinColumn(nullable = true)
	@OneToOne
	private Cozinha cozinha;
	
	@JoinColumn(nullable = true)
	@OneToOne
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Estabelecimento estabelecimento;

}

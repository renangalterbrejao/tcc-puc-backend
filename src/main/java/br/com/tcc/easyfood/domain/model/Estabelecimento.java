package br.com.tcc.easyfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Estabelecimento {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_minima_frete", nullable = false)
	private BigDecimal taxaMinimaFrete;
	
	@Column(nullable = false)
	@Embedded
	private Endereco endereco;
	
	@Column(nullable = false)
	private String categoria;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;
	
	private Boolean permitirAberturaAutomatica = Boolean.FALSE;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private Double notaSatisfacao;
	
	@Column(nullable = false)
	private String descricaoEmpresa;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataAtualizacao;
	
	@ManyToMany
	@JoinTable(name = "estabelecimento_cozinha",
			joinColumns = @JoinColumn(name = "estabelecimento_id") ,
			inverseJoinColumns = @JoinColumn(name = "cozinha_id"))
	private Set<Cozinha> cozinhas = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "estabelecimento_forma_pagamento",
			joinColumns = @JoinColumn(name = "estabelecimento_id") ,
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "estabelecimento_produto",
			joinColumns = @JoinColumn(name = "estabelecimento_id") ,
			inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private Set<Produto> produtos = new HashSet<>();
	
	@OneToMany(mappedBy = "estabelecimento")
	private List<Item> itens = new ArrayList<>();
	
	@OneToOne
	private Horario horario;
	
	@OneToOne
	private Usuario usuario;
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	
	public void abrir() {
	    setAberto(true);
	}

	public void fechar() {
	    setAberto(false);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}

}

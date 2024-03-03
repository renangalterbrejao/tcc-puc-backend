package br.com.tcc.easyfood.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.tcc.easyfood.domain.model.enums.TipoEmbalagem;
import br.com.tcc.easyfood.domain.model.enums.TipoMedida;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("produto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String categoriaProduto;
	
	@Column(nullable = false)
	private String tipoEmbalagem;
	
	@Column(nullable = false)
	private Double quantidadeMedida;
	
	@Column(nullable = false)
	private String tipoMedida;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "dateTime")
	private OffsetDateTime dataCadastro;
	
	@Column(nullable = false)
	@OneToMany(mappedBy = "produto")
	private List<Item> itensProduto = new ArrayList<>();
}

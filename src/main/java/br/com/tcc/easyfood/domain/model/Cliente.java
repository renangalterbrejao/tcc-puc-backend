package br.com.tcc.easyfood.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.br.CPF;

import br.com.tcc.easyfood.domain.model.enums.BandeiraCartao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CPF
	private String cpf;
	
	@Embedded
	private Endereco enderecoCliente;
	
	@Column(nullable = true)
	private String numeroCartao;
	
	@Column(nullable = true, columnDefinition = "dateTime")
	private OffsetDateTime dataValidadeCartao;
	
	@Column(nullable = true)
	private String codigoSegurancaCartao;
	
	@Column(nullable = true)
	private String bandeiraCartao;
	
	@Column(nullable = true)
	private String dataNascimento;
	
	@OneToOne
	private Usuario usuario;

}

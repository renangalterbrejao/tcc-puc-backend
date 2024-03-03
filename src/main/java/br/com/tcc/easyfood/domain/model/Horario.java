package br.com.tcc.easyfood.domain.model;

import java.time.OffsetTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Horario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaSegunda;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaTerca;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaQuarta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaQuinta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaSexta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaSabado;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaAberturaDomingo;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoSegunda;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoTerca;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoQuarta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoQuinta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoSexta;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoSabado;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "time")
	private OffsetTime horaFechamentoDomingo;
	
}

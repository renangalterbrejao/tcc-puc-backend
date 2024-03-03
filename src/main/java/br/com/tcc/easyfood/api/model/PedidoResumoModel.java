package br.com.tcc.easyfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoResumoModel {

	private String id;
	private String codigo;
	private String tipoFormaPagamento;
    private BigDecimal precoFinal;
    private OffsetDateTime dataHorarioConfirmacao;
    private Long estabelecimentoId;
	private String estabelecimentoNome;
	private String estabelecimentoCategoria;
	private BigDecimal estabelecimentoTaxaMinimaFrete;
	private String estabelecimentoNotaSatisfacao;
}

package br.com.tcc.easyfood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoItem {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "item_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Item item;

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

	public Long getItemId() {
		
		if (getItem() != null) {
			return getItem().getId();
		}

		return null;
	}

}
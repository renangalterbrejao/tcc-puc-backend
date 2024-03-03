package br.com.tcc.easyfood.domain.service;

import java.util.List;

import br.com.tcc.easyfood.domain.filter.VendaDiariaFilter;
import br.com.tcc.easyfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
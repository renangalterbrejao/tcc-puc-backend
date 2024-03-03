package br.com.tcc.easyfood.domain.service;

import br.com.tcc.easyfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
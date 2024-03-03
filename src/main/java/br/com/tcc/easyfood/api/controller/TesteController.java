package br.com.tcc.easyfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.model.Estabelecimento;
import br.com.tcc.easyfood.domain.repository.CozinhaRepository;
import br.com.tcc.easyfood.domain.repository.EstabelecimentoRepository;
import br.com.tcc.easyfood.infraestructure.repository.spec.EstabelecimentoSpecs;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findTodasBycategoriaCozinhaContaining(nome);
	}
	
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists(@RequestParam("nome") String nome) {
		return cozinhaRepository.existsByCategoriaCozinha(nome);
	}
	
	@GetMapping("/estabelecimentos/por-nome-e-frete")
	public List<Estabelecimento> estabelecimentosPorNomeFrete(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return estabelecimentoRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/estabelecimentos/com-frete-gratis")
	public List<Estabelecimento> estabelecimentosComFreteGratis(String nome) {
		/*var comFreteGratis = new estabelecimentoComFreteGratisSpec();
		var comNomeSemelhante = new EstabelecimentoComNomeSemelhanteSpec(nome);*/
		
		/*return estabelecimentoRepository.findAll(EstabelecimentoSpecs.comFreteGratis()
				.and(EstabelecimentoSpecs.comNomeSemelhante(nome)));*/
		
		return estabelecimentoRepository.findComFreteGratis(nome);
	}
	
	/*Método que usa customRepository*/
	@GetMapping("/estabelecimentos/primeiro")
	public Optional<Estabelecimento> estabelecimentoPrimeiro() {
		return estabelecimentoRepository.buscarPrimeiro();
	}
	
	/*Método que usa customRepository*/
	@GetMapping("/cozinhas/primeira")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}
}

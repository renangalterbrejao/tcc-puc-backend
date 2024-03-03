package br.com.tcc.easyfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.tcc.easyfood.domain.exception.EntidadeEmUsoException;
import br.com.tcc.easyfood.domain.model.Cozinha;
import br.com.tcc.easyfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void excluir(Long cozinhaId) {

		try {

			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();

		} catch (EmptyResultDataAccessException e) {

			/*
			 * Solução ruim pois mistura conceitos de web(HttpStatus) em uma camada de
			 * service java
			 */
			/*
			 * throw new ResponseStatusException(HttpStatus.NOT_FOUND,
			 * String.format("Cozinha de código %d não existe!!!", cozinhaId));
			 */

			// Solução boa
			throw new CozinhaNaoEncontradaException(cozinhaId);

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));

		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {

		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));

	}

}

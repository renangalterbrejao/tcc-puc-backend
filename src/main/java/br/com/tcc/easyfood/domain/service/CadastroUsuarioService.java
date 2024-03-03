package br.com.tcc.easyfood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.exception.UsuarioNaoEncontradoException;
import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
    	usuarioRepository.detach(usuario);
    	
    	Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
    	
    	if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
    		throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
    	}
    	
        return usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
        usuario.setSenha(novaSenha);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
    
    public Usuario buscarPorEmailESenha(String email, String senha) {
    	
    	if(email == null || email.isBlank()) {
    		throw new NegocioException(String.format("O email do cliente não pode ser nulo ou vazio."));
    	}
    	
    	if(senha == null || senha.isBlank()) {
    		throw new NegocioException(String.format("A senha do cliente não pode ser nula ou vazia."));
    	}
    	
    	Optional<Usuario> usuarioExistente = usuarioRepository.findByEmailAndSenha(email, senha);
    	
    	if(usuarioExistente.isEmpty()) {
    		throw new NegocioException(String.format("Não existe um cliente cadastrado com o e-mail %s ou a senha está incorreta", email));
    	}
    	
        return usuarioExistente.get();
    }
}

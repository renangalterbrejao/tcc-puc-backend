package br.com.tcc.easyfood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.easyfood.api.model.UsuarioClienteModel;
import br.com.tcc.easyfood.domain.exception.ClienteNaoEncontradoException;
import br.com.tcc.easyfood.domain.exception.NegocioException;
import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.repository.ClienteRepository;
import br.com.tcc.easyfood.domain.repository.UsuarioRepository;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Cliente salvar(Cliente cliente) {
    	clienteRepository.detach(cliente);
    	
    	Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(cliente.getUsuario().getEmail());
    	
    	if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(cliente)) {
    		throw new NegocioException(String.format("Já existe um cliente cadastrado com o e-mail %s", cliente.getUsuario().getEmail()));
    	}
    	
        return clienteRepository.save(cliente);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }  
    
    public UsuarioClienteModel controiObjUsuarioClienteModel(Usuario usuario, Cliente cliente) {
    	
    	if(usuario == null || cliente == null) {
    		throw new NegocioException(String.format("Faltam dados para o usuário e/ou para o cliente!"));
    	}
    	
    	UsuarioClienteModel usuarioClienteModel = new UsuarioClienteModel();
    		
        usuarioClienteModel.setId(usuario.getId());
        usuarioClienteModel.setNome(usuario.getNome());
        usuarioClienteModel.setEmail(usuario.getEmail());
        //usuarioClienteModel.setSenha(usuario.getSenha());
        usuarioClienteModel.setContato(usuario.getContato());
        
        usuarioClienteModel.setCpf(cliente.getCpf());
        usuarioClienteModel.setCep(cliente.getEnderecoCliente().getCep());
        usuarioClienteModel.setLogradouro(cliente.getEnderecoCliente().getLogradouro());
        usuarioClienteModel.setNumero(cliente.getEnderecoCliente().getNumero());
        usuarioClienteModel.setComplemento(cliente.getEnderecoCliente().getComplemento());
        usuarioClienteModel.setBairro(cliente.getEnderecoCliente().getBairro());
        usuarioClienteModel.setCidadeId(cliente.getEnderecoCliente().getCidade().getId());
        usuarioClienteModel.setCidade(cliente.getEnderecoCliente().getCidade().getNome());
        usuarioClienteModel.setEstado(cliente.getEnderecoCliente().getCidade().getEstado().getNome());
        usuarioClienteModel.setNumeroCartao(cliente.getNumeroCartao());
        usuarioClienteModel.setDataValidadeCartao(cliente.getDataValidadeCartao().toString());
        usuarioClienteModel.setCodigoSegurancaCartao(cliente.getCodigoSegurancaCartao());
        usuarioClienteModel.setBandeiraCartao(cliente.getBandeiraCartao());
        usuarioClienteModel.setDataNascimento(cliente.getDataNascimento());
        
    	return usuarioClienteModel;
    }
}

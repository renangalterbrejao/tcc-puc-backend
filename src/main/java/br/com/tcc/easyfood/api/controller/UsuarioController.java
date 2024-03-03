package br.com.tcc.easyfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.easyfood.api.assembler.UsuarioInputDisassembler;
import br.com.tcc.easyfood.api.assembler.UsuarioModelAssembler;
import br.com.tcc.easyfood.api.model.UsuarioClienteModel;
import br.com.tcc.easyfood.api.model.UsuarioModel;
import br.com.tcc.easyfood.api.model.input.SenhaInput;
import br.com.tcc.easyfood.api.model.input.UsuarioComSenhaInput;
import br.com.tcc.easyfood.api.model.input.UsuarioInput;
import br.com.tcc.easyfood.domain.model.Cliente;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.repository.UsuarioRepository;
import br.com.tcc.easyfood.domain.service.CadastroClienteService;
import br.com.tcc.easyfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private CadastroClienteService clienteService;
    
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
    
    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;
    
    @GetMapping
    public UsuarioClienteModel buscarUsuarioClientePorEmailESenha(@RequestParam(required = true) String email, @RequestParam(required = true) String senha) {
    	
        Usuario usuario = cadastroUsuario.buscarPorEmailESenha(email, senha);
        
        Cliente cliente = clienteService.buscarOuFalhar(usuario.getId());
        
        UsuarioClienteModel usuarioClienteModel = clienteService.controiObjUsuarioClienteModel(usuario, cliente);
        
        return usuarioClienteModel;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);
        
        return usuarioModelAssembler.toModel(usuario);
    }
    
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
        
        return usuarioModelAssembler.toModel(usuarioAtual);
    }
    
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }            
}

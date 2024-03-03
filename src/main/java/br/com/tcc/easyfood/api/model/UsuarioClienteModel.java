package br.com.tcc.easyfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioClienteModel {
	
	private Long id;
    private String nome;
    private String email;
    private String contato;
    private String cpf;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Long cidadeId;
    private String cidade;
    private String estado;
    private String numeroCartao;
    private String dataValidadeCartao;
    private String codigoSegurancaCartao;
    private String bandeiraCartao;
    private String dataNascimento;

}

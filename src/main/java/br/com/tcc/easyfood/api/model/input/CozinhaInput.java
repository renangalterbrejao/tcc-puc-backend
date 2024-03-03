package br.com.tcc.easyfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaInput {

    @NotBlank
    private String nome;
    
}
package br.com.tcc.easyfood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.tcc.easyfood.core.validation.FileContentType;
import br.com.tcc.easyfood.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "500KB")
	//@FileContentType(allowed = { MediaType.ALL_VALUE })
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
}
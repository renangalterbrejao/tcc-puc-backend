package br.com.tcc.easyfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tcc.easyfood.api.controller.UsuarioController;
import br.com.tcc.easyfood.domain.model.Usuario;
import br.com.tcc.easyfood.domain.service.CadastroUsuarioService;
import br.com.tcc.easyfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@SuppressWarnings("deprecation")
public class CadastroUsuarioTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/usuarios";
	}
	
	@Test
	public void deveRetornarRegistroCorreto_QuandoConsultarUsuarioLoginSenhaCorretos() {
		
		try {
			
			Usuario usuario = cadastroUsuarioService.buscarPorEmailESenha("avaliadortccamericana@gmail.com", "123456");
			
			assertThat(usuario).isNotNull();
			Assert.assertThat(usuario.getId(), is(Long.valueOf(1)));
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	@Test
	public void deveRetornar400_QuandoConsultarUsuarioLoginOuSenhaIncorretos() {
		
		RestAssured.given()
			.param("email", "avaliadortccamericana@gmail.com")
			.param("senha", "1234567")
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	

}

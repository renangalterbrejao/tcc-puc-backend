package br.com.tcc.easyfood;

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

import br.com.tcc.easyfood.api.controller.FluxoPedidoController;
import br.com.tcc.easyfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@SuppressWarnings("deprecation")
public class CadastroPedidoTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private FluxoPedidoController fluxoPedidoController;
	
	private String jsonPedidoCorreto;
	
	private String jsonPedidoIncorreto;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pedidos";
		
		jsonPedidoCorreto = ResourceUtils.getContentFromResource(
                "/json/correto/pedido-correto.json");
		
		jsonPedidoIncorreto = ResourceUtils.getContentFromResource(
                "/json/incorreto/pedido-incorreto.json");
		
	}

	@Test
    public void deveRetornarStatus400_QuandoCadastrarPedidoIncorreto() {
    	RestAssured.given()
            .body(jsonPedidoIncorreto)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
	
	@Test
    public void deveRetornarStatus201_QuandoCadastrarPedidoCorreto() {
    	RestAssured.given()
            .body(jsonPedidoCorreto)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    } 
	
	@Test
    public void deveRetornarStatus400_QuandoConfirmarPedidoJaEntregue() {
		
		RestAssured.given()
	        .pathParam("codigoPedido", "52d01bb0-5ee8-41c5-bb43-946d61956cd8")
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .put("/{codigoPedido}/confirmacao")
	    .then()
	        .statusCode(HttpStatus.BAD_REQUEST.value());
		
    }
	
	@Test
    public void naodeveRetornarExcecao_QuandoConfirmarPedidoCriado() {
    	
		try {
			
			fluxoPedidoController.confirmar("2193b1c0-4959-4ee6-ab58-50ace3394e8e");
		
		} catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
    }
	
	@Test
    public void deveRetornarStatus200_QuandoConsultarMeusPedidos_avaliadorTccAmericana() {
		
		RestAssured.given()
	        .pathParam("usuarioId", Integer.valueOf(1))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/porUsuario/{usuarioId}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
	
	@Test
    public void deveRetornarStatus404_QuandoConsultarMeusPedidos_usuarioInexistente() {
		
		RestAssured.given()
	        .pathParam("usuarioId", Integer.valueOf(10))
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/porUsuario/{usuarioId}")
	    .then()
	        .statusCode(HttpStatus.NOT_FOUND.value());
		
    }
	
	@Test
    public void deveRetornarStatus200_QuandoConsultarItensDoPedido_pedido1() {
		
		RestAssured.given()
	        .pathParam("codigoPedido", "52d01bb0-5ee8-41c5-bb43-946d61956cd8")
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/itensporCodigo/{codigoPedido}")
	    .then()
	        .statusCode(HttpStatus.OK.value());
		
    }
	
	@Test
    public void deveRetornarStatus400_QuandoConsultarItensDoPedido_pedidoInexistente() {
		
		RestAssured.given()
	        .pathParam("codigoPedido", "24d01cc0-5ee8-39c5-0040-12345dd56031")
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/itensporCodigo/{codigoPedido}")
	    .then()
	        .statusCode(HttpStatus.NOT_FOUND.value());
		
    }
	
}

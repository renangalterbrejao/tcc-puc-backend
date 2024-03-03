package br.com.tcc.easyfood;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

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

import br.com.tcc.easyfood.api.controller.EstabelecimentoController;
import br.com.tcc.easyfood.api.controller.EstabelecimentoItemController;
import br.com.tcc.easyfood.api.controller.EstabelecimentoItemFotoController;
import br.com.tcc.easyfood.api.model.EstabelecimentoResumoModel;
import br.com.tcc.easyfood.api.model.FotoProdutoModel;
import br.com.tcc.easyfood.api.model.ItemModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@SuppressWarnings("deprecation")
public class CadastroEstabelecimentoTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private EstabelecimentoController estabelecimentoController;
	
	@Autowired
	private EstabelecimentoItemController estabelecimentoItemController;
	
	@Autowired
	private EstabelecimentoItemFotoController estabelecimentoItemFotoController;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/estabelecimentos";
	}

	@Test
	public void deveRetornarQuatro_QuandoConsultarEstabelecimentosPorCidade_Americana() {
		
		List<EstabelecimentoResumoModel> estabelecimentos = estabelecimentoController.listarPorCidadeId(Long.valueOf(6)) ;
		
		Assert.assertNotNull(estabelecimentos);
		Assert.assertThat(estabelecimentos.size(), is(Integer.valueOf(4)));
		
	}
	
	@Test
	public void deveRetornarDiferenteQuatro_QuandoConsultarEstabelecimentosPorCidade_BeloHorizonte() {
		
		List<EstabelecimentoResumoModel> estabelecimentos = estabelecimentoController.listarPorCidadeId(Long.valueOf(2)) ;
		
		Assert.assertNotNull(estabelecimentos);
		Assert.assertNotEquals(estabelecimentos.size(), is(Integer.valueOf(4)));
		
	}
	
	@Test
    public void deveRetornarStatus200_QuandoConsultarEstabelecimentos() {
    	RestAssured.given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }
	
	@Test
    public void deveRetornarStatus200_QuandoConsultarItensEstabelecimento_AdegaCarvalho() {
    	RestAssured.given()
    		.pathParam("estabelecimentoId", Integer.valueOf(2))
            .accept(ContentType.JSON)
        .when()
            .get("/{estabelecimentoId}/itens")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
	
	@Test
    public void deveRetornarStatus404_QuandoConsultarItensEstabelecimentoInexistente() {
    	RestAssured.given()
    		.pathParam("estabelecimentoId", Integer.valueOf(1000))
            .accept(ContentType.JSON)
        .when()
            .get("/{estabelecimentoId}/itens")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Test
	public void deveRetornarTres_QuandoConsultarItensEstabelecimento_AdegaCarvalho() {
		
		List<ItemModel> estabelecimentoItens = estabelecimentoItemController.listar(Long.valueOf(2), false);
		
		Assert.assertNotNull(estabelecimentoItens);
		Assert.assertThat(estabelecimentoItens.size(), is(Integer.valueOf(3)));
		
	}
	
	@Test
	public void deveRetornarTres_QuandoConsultarItensEstabelecimento_MaxiAtacado() {
		
		List<ItemModel> estabelecimentoItens = estabelecimentoItemController.listar(Long.valueOf(4), false);
		
		Assert.assertNotNull(estabelecimentoItens);
		Assert.assertThat(estabelecimentoItens.size(), is(Integer.valueOf(3)));
		
	}
	
	@Test
    public void deveRetornarStatus200_QuandoConsultarFotoProdutoExistente() {
    	RestAssured.given()
    		.pathParam("estabelecimentoId", Integer.valueOf(3))
    		.pathParam("itemId", Integer.valueOf(1))
            .accept(ContentType.JSON)
        .when()
            .get("/{estabelecimentoId}/itens/{itemId}/foto")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
	
	@Test
    public void deveRetornarStatus404_QuandoConsultarFotoProdutoInexistente() {
    	RestAssured.given()
    		.pathParam("estabelecimentoId", Integer.valueOf(3))
    		.pathParam("itemId", Integer.valueOf(100))
            .accept(ContentType.JSON)
        .when()
            .get("/{estabelecimentoId}/itens/{itemId}/foto")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Test
	public void deveRetornarNomeArquivo_QuandoConsultarFoto() {
		
		try {
			
			FotoProdutoModel fotoProdutoModel = estabelecimentoItemFotoController.buscar(Long.valueOf(3), Long.valueOf(1));
			Assert.assertNotNull(fotoProdutoModel.getNomeArquivo());
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

}

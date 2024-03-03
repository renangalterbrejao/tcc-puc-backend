package br.com.tcc.easyfood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("easyfood.storage")
public class StorageProperties {

	private Local local = new Local();
	private S3 s3 = new S3();
	private TipoStorage tipo = TipoStorage.LOCAL;
	
	public enum TipoStorage {
		
		LOCAL, S3
		
	}
	
	@Getter
	@Setter
	public class Local {
		
		private Path diretorioFotos;
		
	}
	
	@Getter
	@Setter
	public class S3 {
		
		//private String idChaveAcesso;
		//private String chaveAcessoSecreta;
		// Inclui os valores das chaves de acesso e secreta do bucket na Amazon para poder fazer funcionar temporariamente através do container Docker
		private String idChaveAcesso = "AKIA5KL4D2E3PP3B62FX";
		private String chaveAcessoSecreta = "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";
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
		
	}
	
}
package br.com.tcc.easyfood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.tcc.easyfood.domain.service.EnvioEmailService;
import br.com.tcc.easyfood.infraestructure.service.email.FakeEnvioEmailService;
import br.com.tcc.easyfood.infraestructure.service.email.SandboxEnvioEmailService;
import br.com.tcc.easyfood.infraestructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		
		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEnvioEmailService();
			case SMTP:
				return new SmtpEnvioEmailService();
			case SANDBOX:
			    return new SandboxEnvioEmailService();
			default:
				return null;
		}
	}

}
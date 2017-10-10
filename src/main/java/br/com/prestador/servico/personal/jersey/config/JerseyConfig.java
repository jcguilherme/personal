package br.com.prestador.servico.personal.jersey.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.prestador.servico.personal.endpoint.UsuarioEndpoint;

@Component
@ApplicationPath("/personal")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(UsuarioEndpoint.class);
	}
}

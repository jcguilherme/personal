package br.com.prestador.servico.personal;
import java.sql.Connection;
import org.postgresql.Driver;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.prestador.servico.personal.teste.JerseyClient;
@SpringBootApplication
public class ApplicationStarter {  
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		//JerseyClient.main(args);
    }       
}            
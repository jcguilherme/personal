package br.com.prestador.servico.personal;
import java.sql.Connection;
import org.postgresql.Driver;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ApplicationStarter {  
	public static void main(String[] args) {
		
			/**
spring.datasource.url=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=
spring.datasource.password=
			 */
		
		SpringApplication.run(ApplicationStarter.class, args);
	}      
}            
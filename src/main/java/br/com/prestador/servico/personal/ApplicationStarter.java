package br.com.prestador.servico.personal;
import java.sql.Connection;
import org.postgresql.Driver;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ApplicationStarter {  
	public static void main(String[] args) {
		try{
			/**
spring.datasource.url=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=
spring.datasource.password=
			 */
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		   "jdbc:postgres://ec2-107-22-252-91.compute-1.amazonaws.com:5432/d2t53ug89hsgap"
				,"loasagnmxipukr", "adf91c1828914072c24c1f8e5dc348500d6dcf7070279151d79675a1b71f6c18");
		connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		SpringApplication.run(ApplicationStarter.class, args);
    }       
}            
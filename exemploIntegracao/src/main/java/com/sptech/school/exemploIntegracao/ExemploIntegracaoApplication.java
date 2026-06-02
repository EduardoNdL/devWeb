package com.sptech.school.exemploIntegracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExemploIntegracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploIntegracaoApplication.class, args);
	}

}

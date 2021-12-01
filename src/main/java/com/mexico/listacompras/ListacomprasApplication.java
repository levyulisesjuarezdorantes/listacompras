package com.mexico.listacompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ListacomprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListacomprasApplication.class, args);
	}

}

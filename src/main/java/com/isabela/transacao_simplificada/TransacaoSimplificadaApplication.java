package com.isabela.transacao_simplificada;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients

public class TransacaoSimplificadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoSimplificadaApplication.class, args);
	}

}

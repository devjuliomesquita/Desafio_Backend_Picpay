package com.juliomesquita.picpay_simplificado.core.commom.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI30() {
        return new OpenAPI()
                .info(new Info().
                        title("PicPay Simplificado")
                        .version("1.0")
                        .summary("API Picpay simplificado.")
                        .description("Está API é faz parte de um desafio proposto pelo Picpay.")
                        .termsOfService("juliocesarmcamilo@gmail.com")
                        .contact(new Contact().name("Júlio Mesquita").email("juliocesarmcamilo@gmail.com").url("juliocesarmcamilo@gmail.com"))
                        .license(new License().name("Júlio Mesquita - Licensa MIT").url("juliocesarmcamilo@gmail.com")))
                .servers(List.of( new Server().description("Ambiente LOCAL").url("http://localhost:8080")));
    }
}

package br.com.alfac.food.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        Info info = new Info();
        info.title("API Controle Pedidos");
        info.version("0.2");

        return new OpenAPI().info(info);
    }
    
}

package br.com.alfac.foodcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.alfac.foodcliente")
public class FoodClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodClienteApplication.class, args);
    }

}

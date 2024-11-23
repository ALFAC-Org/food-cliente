package br.com.alfac.foodcliente.unit;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void loadEnv() {
        Dotenv.configure()
                .filename("config/env/test.env")
                .load();
    }
}

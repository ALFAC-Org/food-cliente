import br.com.alfac.foodcliente.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = br.com.alfac.foodcliente.FoodClienteApplication.class)
public class FoodClienteApplicationTest extends BaseTest {

    @Test
    void load() {
        System.out.println("Starting the FoodApplicationTest file...");
    }

}
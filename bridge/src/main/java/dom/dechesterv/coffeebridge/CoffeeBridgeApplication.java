package dom.dechesterv.coffeebridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoffeeBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeBridgeApplication.class, args);
    }

}


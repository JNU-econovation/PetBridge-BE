package PetBridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetBridgeApplication {

	public static void main(String[] args) {

		SpringApplication.run(PetBridgeApplication.class, args);
	}

}
package gdg.daejuju.daehakjumak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DaehakjumakApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaehakjumakApplication.class, args);
	}

}

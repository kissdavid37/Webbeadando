package hu.unideb.inf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import hu.unideb.inf.repository.UserRepository;
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan("hu.unideb.inf.model")
@SpringBootApplication
public class WebBeadandoBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBeadandoBackApplication.class, args);
	}

}

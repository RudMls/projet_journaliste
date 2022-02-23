package fr.ut1.miage;

import fr.ut1.miage.model.InstitutFormation;
import fr.ut1.miage.service.impl.InstitutFormationServiceImpl;
import fr.ut1.miage.util.Constant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetJournalisteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJournalisteApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(InstitutFormationServiceImpl institutFormationService) {
//		return args -> {
//			Constant.CONSTRAINT_NOMIF.forEach(s -> {
//				institutFormationService.create(new InstitutFormation(s));
//			});
//		};
//	}
}

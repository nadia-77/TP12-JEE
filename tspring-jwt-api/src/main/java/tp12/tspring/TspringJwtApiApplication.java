package tp12.tspring;


import tp12.tspring.entities.*;
import tp12.tspring.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TspringJwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TspringJwtApiApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepo,
	                                  RoleRepository roleRepo,
	                                  BCryptPasswordEncoder encoder) {
		return args -> {
			if (roleRepo.count() == 0) {
				Role adminRole = roleRepo.save(new Role(null, "ROLE_ADMIN"));
				Role userRole  = roleRepo.save(new Role(null, "ROLE_USER"));

				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("1234"));
				admin.setActive(true);
				admin.getRoles().add(adminRole);
				userRepo.save(admin);

				User user = new User();
				user.setUsername("user");
				user.setPassword(encoder.encode("1111"));
				user.setActive(true);
				user.getRoles().add(userRole);
				userRepo.save(user);

				System.out.println("✅ Données initialisées !");
			}
		};
	}
}
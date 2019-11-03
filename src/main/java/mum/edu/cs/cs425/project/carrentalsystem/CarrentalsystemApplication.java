package mum.edu.cs.cs425.project.carrentalsystem;

import mum.edu.cs.cs425.project.carrentalsystem.model.Credential;
import mum.edu.cs.cs425.project.carrentalsystem.model.Role;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.CredentialRepository;
import mum.edu.cs.cs425.project.carrentalsystem.repositary.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication()
public class CarrentalsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrentalsystemApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(CredentialRepository credentialRepository,
//                                      BCryptPasswordEncoder bCryptPasswordEncoder,
//                                      RoleRepository roleRepository) {
//        return (args) -> {
//            // create admin account
//            Set<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByRole("ADMIN"));
//
//            credentialRepository.save(new Credential("admin@mum.edu", bCryptPasswordEncoder.encode("admin"), true, roles));
//
//        };
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}

package com.mario.backendbasicbcp;

import com.mario.backendbasicbcp.model.Coin;
import com.mario.backendbasicbcp.model.ExchangeRate;
import com.mario.backendbasicbcp.model.Role;
import com.mario.backendbasicbcp.model.User;
import com.mario.backendbasicbcp.repository.CoinRepository;
import com.mario.backendbasicbcp.repository.ExchangeRateRepository;
import com.mario.backendbasicbcp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootApplication
public class BackendBasicBcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBasicBcpApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ExchangeRateRepository exchangeRateRepository, UserRepository userRepository, CoinRepository coinRepository) {

        return args -> {

            User userAdmin = userRepository.findByUsername("admin").orElse(new User());
            userAdmin.setUsername("admin");
            userAdmin.setPassword("$2a$10$LeD8I1HYCT6ktDkLeUG3i.1PeYpSBl1x7vhe4pxybWJej50P7L2Uu");

            User user = userRepository.findByUsername("user").orElse(new User());
            user.setUsername("user");
            user.setPassword("$2a$10$KprwixuKhuiyRRzxZd2h2.Nimn3u.tGTthb6Z6KsLd3.jQAUJp4mu");

            if(userAdmin.getUserId() == null) {

                List<Role> roles = new ArrayList<>();
                roles.add(new Role("ADMIN"));
                userAdmin.setRoles(roles);
            }

            if(user.getUserId() == null) {

                List<Role> roles = new ArrayList<>();
                roles.add(new Role("USER"));
                user.setRoles(roles);
            }
            userRepository.save(userAdmin);
            userRepository.save(user);


        };
    }


}

package org.konurbaev.userrole;


import org.konurbaev.userrole.model.Role;
import org.konurbaev.userrole.model.RoleRepository;
import org.konurbaev.userrole.model.User;
import org.konurbaev.userrole.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
class App {

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        return (evt) -> {
            Role role = new Role("My Role 1");
            roleRepository.save(role);

            User user = new User("konurbaev-e");
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            user.setUserrole(roleList);
            userRepository.save(user);

            role = new Role("My Role 2");
            roleRepository.save(role);
            role = new Role("My Role 3");
            roleRepository.save(role);
            role = new Role("My Role 4");
            roleRepository.save(role);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

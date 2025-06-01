package com.newgen.user.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.newgen.user.model.UserRepository;
import com.newgen.user.model.User;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Check if data already exists
//        if (userRepository.count() < 10) {
//            // Insert initial users
//            userRepository.saveAll(List.of(
//                new User(11, "John Doe"),
//                new User(12, "Jane Doe"),
//                new User(13, "Alice"),
//                new User(14, "Bob")
//            ));
//            System.out.println("Initial data added.");
//        }
    }
}
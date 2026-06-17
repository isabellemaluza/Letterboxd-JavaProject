package com.example.letterboxd.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrap implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public AdminBootstrap(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM usuario WHERE username = 'admin'",
            Integer.class
        );

        if (count == null || count == 0) {
            String senha = passwordEncoder.encode("admin123");
            jdbcTemplate.update(
                "INSERT INTO usuario (username, password, role) VALUES (?, ?, ?)",
                "admin", senha, "ADMIN"
            );
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}
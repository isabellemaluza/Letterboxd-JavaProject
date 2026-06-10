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
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS usuario (
                id SERIAL PRIMARY KEY,
                username VARCHAR(100) UNIQUE NOT NULL,
                password VARCHAR(255) NOT NULL
            )
        """);

        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM usuario WHERE username = 'admin'",
            Integer.class
        );

        if (count == null || count == 0) {
            String senha = passwordEncoder.encode("admin123");
            jdbcTemplate.update(
                "INSERT INTO usuario (username, password) VALUES (?, ?)",
                "admin", senha
            );
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}
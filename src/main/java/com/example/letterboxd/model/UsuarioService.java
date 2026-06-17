package com.example.letterboxd.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> users = jdbcTemplate.query(
            "SELECT username, password, role FROM usuario WHERE username = ?",
            (rs, rowNum) -> User.builder()
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .roles(rs.getString("role"))
                .build(),
            username
        );

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return users.get(0);
    }

    public void registrar(String username, String password, PasswordEncoder encoder) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM usuario WHERE username = ?",
            Integer.class, username
        );

        if (count != null && count > 0) {
            throw new IllegalArgumentException("Nome de usuário já existe.");
        }

        jdbcTemplate.update(
            "INSERT INTO usuario (username, password, role) VALUES (?, ?, ?)",
            username, encoder.encode(password), "CLIENTE"
        );
    }
}
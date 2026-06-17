package com.example.letterboxd.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letterboxd.model.UsuarioService;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        try {
            usuarioService.registrar(username, password, passwordEncoder);
            return "redirect:/login?registrado";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "registro";
        }
    }
}
package com.example.letterboxd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuController {

    private final FilmeService filmeService;

    public MenuController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/p1")
    public String pagina1(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "p1";
    }

    @GetMapping("/p2")
    public String pagina2() {
        return "p2";
    }

    @GetMapping("/formfilme")
    public String formularioFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "formfilme";
    }

    @PostMapping("/salvarfilme")
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeService.salvar(filme);
        return "sucesso";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}
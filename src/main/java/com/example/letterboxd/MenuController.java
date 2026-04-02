package com.example.letterboxd;

import java.util.List;

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
        return "redirect:/listar";
    }

    @GetMapping("/listar")
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeService.listarTodos();
        System.out.println("FILMES ENCONTRADOS: " + filmes.size());
        model.addAttribute("filmes", filmes);
        return "listarfilme";
    }
}
package com.example.letterboxd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.letterboxd.model.Filme;
import com.example.letterboxd.model.FilmeService;

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
    public String salvarFilme(@ModelAttribute Filme filme, Model model) {
        try {
            filmeService.salvar(filme);
            return "redirect:/listar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("filme", filme);
            return "formfilme";
        }
    }

    @GetMapping("/listar")
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeService.listarTodos();
        model.addAttribute("filmes", filmes);
        return "listarfilme";
    }

    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Integer id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "formfilme";
    }

    @PostMapping("/atualizarfilme")
    public String atualizarFilme(@ModelAttribute Filme filme, Model model) {
        try {
            filmeService.atualizar(filme);
            return "redirect:/listar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("filme", filme);
            return "formfilme";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Integer id) {
        filmeService.deletar(id);
        return "redirect:/listar";
    }
}
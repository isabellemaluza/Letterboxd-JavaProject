package com.example.letterboxd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.letterboxd.model.Filme;
import com.example.letterboxd.model.FilmeService;

@FilmeRestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class FilmeRestController {

    private final FilmeService filmeService;

    public FilmeRestController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/filmes")
    public List<Filme> listarFilmes() {
        return filmeService.listarTodos();
    }

    @GetMapping("/filmes/{id}")
    public Filme mostrarFilme(@PathVariable Integer id) {
        return filmeService.buscarPorId(id);
    }

    @PostMapping("/filmes")
    public Filme inserirFilme(@RequestBody Filme filme) {
        filmeService.salvar(filme);
        return filme;
    }
}
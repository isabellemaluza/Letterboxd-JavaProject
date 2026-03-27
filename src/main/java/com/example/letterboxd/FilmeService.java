package com.example.letterboxd;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    private final FilmeDAO filmeDAO;

    public FilmeService(FilmeDAO filmeDAO) {
        this.filmeDAO = filmeDAO;
    }

    public void salvar(Filme filme) {
        filmeDAO.salvar(filme);
    }

    public List<Filme> listarTodos() {
        return filmeDAO.listarTodos();
    }
}

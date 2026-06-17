package com.example.letterboxd.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    private final FilmeDAO filmeDAO;

    public FilmeService(FilmeDAO filmeDAO) {
        this.filmeDAO = filmeDAO;
    }

    public void salvar(Filme filme, String username) {
        validar(filme);
        filme.setCadastradoPor(username);
        filmeDAO.salvar(filme);
    }

    public List<Filme> listarTodos() {
        return filmeDAO.listarTodos();
    }

    public Filme buscarPorId(Integer id) {
        return filmeDAO.buscarPorId(id);
    }

    public void atualizar(Filme filme) {
        validar(filme);
        filmeDAO.atualizar(filme);
    }

    public void deletar(Integer id) {
        filmeDAO.deletar(id);
    }

    private void validar(Filme filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (filme.getGenero() == null || filme.getGenero().isBlank()) {
            throw new IllegalArgumentException("Gênero é obrigatório.");
        }
        if (filme.getNota() == null) {
            throw new IllegalArgumentException("Nota é obrigatória.");
        }
    }
}
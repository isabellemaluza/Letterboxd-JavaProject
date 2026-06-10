package com.example.letterboxd.model;

import java.util.List;

public interface FilmeDAO {

    void salvar(Filme filme);

    List<Filme> listarTodos();

    Filme buscarPorId(Integer id);

    void atualizar(Filme filme);

    void deletar(Integer id);
}
package com.example.letterboxd;

import java.util.List;

public interface FilmeDAO {

    void salvar(Filme filme);

    List<Filme> listarTodos();
}
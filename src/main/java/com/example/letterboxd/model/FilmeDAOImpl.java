package com.example.letterboxd.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FilmeDAOImpl implements FilmeDAO {

    private final JdbcTemplate jdbcTemplate;

    public FilmeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Filme> mapper = (rs, rowNum) -> {
        Filme f = new Filme();
        f.setId(rs.getInt("id"));
        f.setTitulo(rs.getString("titulo"));
        f.setDiretor(rs.getString("diretor"));
        f.setGenero(rs.getString("genero"));
        f.setAno(rs.getInt("ano"));
        f.setNota(rs.getDouble("nota"));
        f.setComentario(rs.getString("comentario"));
        return f;
    };

    @Override
    public void salvar(Filme filme) {
        String sql = """
            INSERT INTO filme (titulo, diretor, genero, ano, nota, comentario)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
            filme.getTitulo(),
            filme.getDiretor(),
            filme.getGenero(),
            filme.getAno(),
            filme.getNota(),
            filme.getComentario()
        );
    }

    @Override
    public List<Filme> listarTodos() {
        String sql = "SELECT * FROM filme ORDER BY id DESC";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Filme buscarPorId(Integer id) {
        String sql = "SELECT * FROM filme WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public void atualizar(Filme filme) {
        String sql = """
            UPDATE filme SET titulo = ?, diretor = ?, genero = ?, ano = ?, nota = ?, comentario = ?
            WHERE id = ?
        """;
        jdbcTemplate.update(sql,
            filme.getTitulo(),
            filme.getDiretor(),
            filme.getGenero(),
            filme.getAno(),
            filme.getNota(),
            filme.getComentario(),
            filme.getId()
        );
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM filme WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
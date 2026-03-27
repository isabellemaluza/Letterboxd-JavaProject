CREATE TABLE IF NOT EXISTS filme (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    diretor VARCHAR(120) NOT NULL,
    genero VARCHAR(80) NOT NULL,
    ano INTEGER NOT NULL,
    nota DECIMAL(3,1) NOT NULL,
    comentario TEXT
);
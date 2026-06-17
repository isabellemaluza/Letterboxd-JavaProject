CREATE TABLE IF NOT EXISTS filme (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    diretor VARCHAR(120),
    genero VARCHAR(80) NOT NULL,
    ano INTEGER,
    nota DECIMAL(3,1) NOT NULL,
    comentario TEXT,
    cadastrado_por VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'CLIENTE'
);
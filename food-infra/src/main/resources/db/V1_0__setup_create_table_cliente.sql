CREATE DATABASE IF NOT EXISTS pedidos;

USE pedidos;

CREATE TABLE IF NOT EXISTS cliente (
    id int auto_increment  primary key,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', '')))
);

create unique index cliente_cpf_uk
    on cliente (cpf);

create unique index cliente_email_uk
    on cliente (email);

INSERT INTO cliente(cpf, nome, email) VALUES ("11111111111", 'Joaquim Da Silva', 'joaquim@teste.com');
INSERT INTO cliente(cpf, nome, email) VALUES ("22222222222", 'Maria Joaquim Da Silva', 'maria@teste.com');

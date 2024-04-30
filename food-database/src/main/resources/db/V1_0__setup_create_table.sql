CREATE DATABASE IF NOT EXISTS pedidos;

USE pedidos;

CREATE TABLE IF NOT EXISTS Cliente (
    cpf VARCHAR(11),
    nome VARCHAR(100)
    );

CREATE TABLE IF NOT EXISTS Pedido (
    idPedido INT
);

CREATE TABLE IF NOT EXISTS Teste (
    idTeste INT
);


INSERT INTO Cliente VALUES ("22233344400", 'Joaquim');

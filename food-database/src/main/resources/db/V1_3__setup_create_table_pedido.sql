USE pedidos;

CREATE TABLE IF NOT EXISTS pedido (
    id int auto_increment  primary key,
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', ''))),
    id_cliente int NULL,
    status_pedido VARCHAR(30) NOT NULL,
    data_cadastro datetime NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS combo (
    id int auto_increment  primary key,
    id_pedido int NULL
);

CREATE TABLE IF NOT EXISTS item_combo (
    id int auto_increment  primary key,
    id_combo int NULL,
    id_item int NOT NULL,
    preco decimal(10,2) NOT NULL,
    observacoes VARCHAR(100) NULL
);

CREATE TABLE IF NOT EXISTS item_combo_complemento (
    id int auto_increment  primary key,
    id_item_combo int NOT NULL,
    id_item int NOT NULL,
    preco decimal(10,2) NOT NULL
);

ALTER TABLE pedido ADD CONSTRAINT pedido_fk1 
FOREIGN KEY(id_cliente) REFERENCES cliente(id);

ALTER TABLE combo ADD CONSTRAINT combo_fk1
FOREIGN KEY(id_pedido) REFERENCES pedido(id);

ALTER TABLE item_combo ADD CONSTRAINT item_combo_fk1
FOREIGN KEY(id_combo) REFERENCES combo(id);

ALTER TABLE item_combo ADD CONSTRAINT item_combo_fk2
FOREIGN KEY(id_item) REFERENCES item(id);

ALTER TABLE item_combo_complemento ADD CONSTRAINT item_combo_complemento_fk1
FOREIGN KEY(id_item_combo) REFERENCES item_combo(id);

ALTER TABLE item_combo_complemento ADD CONSTRAINT item_combo_complemento_fk2
FOREIGN KEY(id_item) REFERENCES item(id);

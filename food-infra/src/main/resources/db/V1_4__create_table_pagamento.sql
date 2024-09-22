USE fooddb;

CREATE TABLE pagamento (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   pedido_id BIGINT NOT NULL,
   status ENUM('PENDENTE', 'APROVADO', 'CANCELADO') NOT NULL,
   data_pagamento DATETIME NOT NULL,
   data_atualizacao DATETIME NOT NULL
);
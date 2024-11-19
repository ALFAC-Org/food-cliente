USE foodcliente;

INSERT INTO cliente(cpf, nome, email, uuid) VALUES ('33333333333', 'Carlota Joaquina', 'carlota@joaquina.com', UNHEX(REPLACE(UUID(), '-', '')));
INSERT INTO cliente(cpf, nome, email, uuid) VALUES ('44444444444', 'Marcos Hernandes', 'marcos@hernandes.com', UNHEX(REPLACE(UUID(), '-', '')));
INSERT INTO cliente(cpf, nome, email, uuid) VALUES ('55555555555', 'Luciana Barbosa', 'luciana@barbosa.com ', UNHEX(REPLACE(UUID(), '-', '')));
INSERT INTO cliente(cpf, nome, email, uuid) VALUES ('66666666666', 'Jose Martino', 'jose@martino.com', UNHEX(REPLACE(UUID(), '-', '')));

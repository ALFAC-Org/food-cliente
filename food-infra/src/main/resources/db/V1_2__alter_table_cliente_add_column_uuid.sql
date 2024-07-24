
alter table cliente add uuid BINARY(16) null;

create unique index cliente_uuid_uindex on cliente (uuid);

DELIMITER //
    CREATE PROCEDURE update_uuids()

    BEGIN
        DECLARE done INT DEFAULT 0;
        DECLARE id_val INT;
        DECLARE uuid_val BINARY(16);
        DECLARE cur CURSOR FOR SELECT id FROM cliente;
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

        -- Log para indicar que a procedure foi chamada
        SELECT 'A procedure update_uuids() foi chamada.' AS log_message;

        OPEN cur;
            read_loop: LOOP
                FETCH cur INTO id_val;
                IF done THEN
                        LEAVE read_loop;
                END IF;

                -- Gerar um novo UUID único em formato binário
                SET uuid_val = UNHEX(REPLACE(UUID(), '-', ''));

                -- Exibir os dados antes de atualizar
                SELECT 'Antes de atualizar:', id_val, HEX(uuid) AS 'UUID Atual' FROM cliente WHERE id = id_val;

                -- Atualizar a coluna uuid
                UPDATE cliente SET uuid = uuid_val WHERE id = id_val;

                -- Exibir os dados depois de atualizar
                SELECT 'Depois de atualizar:', id_val, HEX(uuid_val) AS 'Novo UUID';
            END LOOP;
        CLOSE cur;
    END //

DELIMITER ;

CALL update_uuids();

DROP PROCEDURE IF EXISTS update_uuids;

alter table cliente
    modify uuid binary(16) not null default(uuid());

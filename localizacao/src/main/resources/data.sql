CREATE TABLE tb_cidade (
    id_cidade BIGINT NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    qtd_habitantes BIGINT
);

INSERT INTO tb_cidade
    (id_cidade, nome, qtd_habitantes)
VALUES
    (1, 'São Paulo', 12396372),
    (2, 'Rio de Janeiro', 10000000),
    (3, 'Fortaleza', 80000000);

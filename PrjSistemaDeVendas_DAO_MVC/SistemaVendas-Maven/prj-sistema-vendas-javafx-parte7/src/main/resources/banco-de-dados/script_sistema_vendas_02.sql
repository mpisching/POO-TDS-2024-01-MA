CREATE DATABASE IF NOT EXISTS db_vendas2;
USE db_vendas2;

CREATE TABLE categoria(
   id int NOT NULL auto_increment,
   descricao  varchar(50) NOT NULL,
   CONSTRAINT pk_categoria
      PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE produto(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   descricao varchar(200),
   preco decimal(10,2) NOT NULL,
   id_categoria int NOT NULL ,
   CONSTRAINT pk_produto
      PRIMARY KEY(id),
   CONSTRAINT fk_produto_categoria
      FOREIGN KEY(id_categoria)
      REFERENCES categoria(id)
) engine=InnoDB;

/*TABELA ESTOQUE COM RELACIONAMENTO 1:1 PARA PRODUTO*/
CREATE TABLE estoque(
	id_produto INT NOT NULL REFERENCES produto(id),
    quantidade INT NOT NULL DEFAULT 0,
    qtd_minima INT DEFAULT 0,
    qtd_maxima INT DEFAULT 0,
    situacao ENUM('ATIVO', 'INATIVO', 'BLOQUEADO') NOT NULL DEFAULT 'INATIVO',
    CONSTRAINT pk_estoque PRIMARY KEY (id_produto),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (id_produto) REFERENCES produto(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE cliente(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   cpf varchar(50) NOT NULL,
   telefone varchar(50) NOT NULL,
   email varchar(100),
   endereco varchar(100),
   data_nascimento date,
   CONSTRAINT pk_cliente
      PRIMARY KEY(id)
) engine=InnoDB;

INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Edgar','111.111.111-11','(11) 1111-1111', 'edgar@ifsc.edu.br', 'av. mauro ramos', '1970-04-20');
INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Marilene','222.222.222-22','(22) 2222-2121', 'marilene@ifsc.edu.br', 'av. mauro ramos', '1979-10-18');
INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Carla','333.333.333-33','(33) 3333-3333', 'carla@ifsc.edu.br', 'av. mauro ramos', '1986-12-12');

INSERT INTO categoria(descricao) VALUES('Eletrônicos');
INSERT INTO categoria(descricao) VALUES('Vestuário');

INSERT INTO produto(nome, descricao, preco, id_categoria) VALUES('TV 32 LG', 'TV Tela Plana 32 4k', '2000.00', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria) VALUES('TV 40 SAMSUNG', 'TV Tela Plana 42 4k', '3000.00', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria) VALUES('TÊNIS NIKE', 'Tênis Nike Tri Fusion Run 40', '550.50', '2');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria) VALUES('TÊNIS ADIDAS', 'Tênis Adidas Run Ultraboost', '750.00', '2');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);

UPDATE estoque SET quantidade=10, qtd_minima=2, qtd_maxima=100, situacao='ATIVO' WHERE id_produto = 1;
UPDATE estoque SET quantidade=5, qtd_minima=2, qtd_maxima=150, situacao='INATIVO' WHERE id_produto = 2;
UPDATE estoque SET quantidade=100, qtd_minima=2, qtd_maxima=500, situacao='BLOQUEADO' WHERE id_produto = 3;
UPDATE estoque SET quantidade=300, qtd_minima=50, qtd_maxima=1000, situacao='ATIVO' WHERE id_produto = 4;

/* POR SE TRATAR DE UMA RELAÇÃO 1:1, UMA TENTATIVA DE INSERÇÃO DE UM NOVO ESTOQUE PARA UM PRODUTO DARÁ ERRO */
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (1, 20, 2, 100, 'ATIVO');*/
/* A TENTATIVA DE INSERIR UM ESTOQUE SEM QUE O PRODUTO EXISTA TAMBÉM CAUSARÁ ERRO*/
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (4, 20, 2, 100, 'ATIVO');*/
DELETE FROM produto WHERE id = 4;
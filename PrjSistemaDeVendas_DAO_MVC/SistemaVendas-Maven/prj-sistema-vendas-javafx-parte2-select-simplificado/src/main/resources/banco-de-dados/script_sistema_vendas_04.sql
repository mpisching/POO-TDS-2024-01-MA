CREATE DATABASE IF NOT EXISTS db_vendas4;
USE db_vendas4;

/*TABELAS PARA COMPORTAR O CONCEITO DE HERANÇA - 1 TABELA POR ENTIDADE 
TABELA FORNECEDOR
TABELAS NACIONAL E INTERNACIONAL
*/
CREATE TABLE fornecedor(
	id int NOT NULL auto_increment,
    nome varchar(50) NOT NULL,
    email varchar(100),
    fone varchar(20),
    CONSTRAINT pk_fornecedor PRIMARY KEY(id)
) engine=InnoDB;

CREATE TABLE nacional(
	id_fornecedor INT NOT NULL REFERENCES fornecedor(id),
    cnpj VARCHAR(20) NOT NULL,
    CONSTRAINT pk_nacional PRIMARY KEY (id_fornecedor),
    CONSTRAINT fk_nacional_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id) 
		ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=InnoDB;

CREATE TABLE internacional(
	id_fornecedor INT NOT NULL REFERENCES fornecedor(id),
    nif VARCHAR(20) NOT NULL,
    pais VARCHAR(30) NOT NULL,
    CONSTRAINT pk_internacional PRIMARY KEY (id_fornecedor),
    CONSTRAINT fk_internacional_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id) 
		ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=InnoDB;

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
   id_categoria int NOT NULL,
   id_fornecedor int NOT NULL,
   CONSTRAINT pk_produto
      PRIMARY KEY(id),
   CONSTRAINT fk_produto_categoria
      FOREIGN KEY(id_categoria)
      REFERENCES categoria(id),
   CONSTRAINT fk_produto_fornecedor
      FOREIGN KEY(id_fornecedor)
      REFERENCES fornecedor(id)      
) engine=InnoDB;

/*TABELA ESTOQUE COM RELACIONAMENTO 1:1 PARA PRODUTO*/
CREATE TABLE estoque(
	id_produto INT NOT NULL REFERENCES produto(id),
    quantidade INT NOT NULL DEFAULT 0,
    qtd_minima INT DEFAULT 0,
    qtd_maxima INT DEFAULT 0,
    situacao ENUM('ATIVO', 'INATIVO', 'BLOQUEADO') NOT NULL DEFAULT 'INATIVO',
    CONSTRAINT pk_estoque PRIMARY KEY (id_produto),
    CONSTRAINT fk_estoque_produto 
		FOREIGN KEY (id_produto) 
        REFERENCES produto(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
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

/*TABELAS PARA IMPLEMENTAÇÃO DO CONCEITO DE CLASSES ASSOCIATIVAS OU CLASSES INTERMEDIÁRIAS, QUE 
  IMPLEMENTAM A RELAÇÃO DE MULTIPLICIDADE MUITOS PARA MUITOS (M:N)
  TABELAS: VENDAS, ITEM_DE_VENDA, PRODUTO E CLIENTE (estas duas últimas, já implementada)
  */
CREATE TABLE venda(
   id int NOT NULL auto_increment,
   data date NOT NULL,
   total decimal(10,2) NOT NULL,
   pago boolean NOT NULL,
   taxa_desconto double,
   empresa varchar(50) NOT NULL,
   situacao ENUM('FINALIZADA', 'CANCELADA', 'ABERTA') NOT NULL DEFAULT 'ABERTA',
   id_cliente int not null,
   CONSTRAINT pk_venda
      PRIMARY KEY(id),
   CONSTRAINT fk_venda_cliente
      FOREIGN KEY(id_cliente)
      REFERENCES cliente(id)
) engine=InnoDB;

CREATE TABLE item_de_venda(
   id int NOT NULL auto_increment,
   quantidade int NOT NULL,
   valor decimal(10,2) NOT NULL,
   id_produto int NOT NULL,
   id_venda int NOT NULL,
   CONSTRAINT pk_item_de_venda
      PRIMARY KEY(id),
   CONSTRAINT fk_itemdevenda_produto
      FOREIGN KEY(id_produto)
      REFERENCES produto(id),
   CONSTRAINT fk_itemdevenda_vendas
      FOREIGN KEY(id_venda)
      REFERENCES venda(id)
      ON DELETE CASCADE
) engine=InnoDB;  
/*FIM DA IMPLEMENTAÇÃO DAS TABELAS M:N */

INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Edgar','111.111.111-11','(11) 1111-1111', 'edgar@ifsc.edu.br', 'av. mauro ramos', '1970-04-20');
INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Marilene','222.222.222-22','(22) 2222-2121', 'marilene@ifsc.edu.br', 'av. mauro ramos', '1979-10-18');
INSERT INTO cliente(nome, cpf, telefone, email, endereco, data_nascimento) VALUES('Carla','333.333.333-33','(33) 3333-3333', 'carla@ifsc.edu.br', 'av. mauro ramos', '1986-12-12');

INSERT INTO fornecedor(nome, email, fone) VALUES('FORNEXPRESS', 'contact@fornexpress.com', '56564343');
INSERT INTO internacional(id_fornecedor, nif, pais) VALUES((SELECT max(id) FROM fornecedor), '123321', 'EUA');
INSERT INTO fornecedor(nome, email, fone) VALUES('SULBRAS', 'contact@sulbras.com', '11-2345-5432');
INSERT INTO nacional(id_fornecedor, cnpj) VALUES((SELECT max(id) FROM fornecedor), '11.719.809/0001-94');
INSERT INTO fornecedor(nome, email, fone) VALUES('CLIPS', 'contact@clips.com.br', '48-8877-8888');
INSERT INTO nacional(id_fornecedor, cnpj) VALUES((SELECT max(id) FROM fornecedor), '11.061.852/0001-72');


INSERT INTO categoria(descricao) VALUES('Eletrônicos');
INSERT INTO categoria(descricao) VALUES('Vestuário');

INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TV 32 LG', 'TV Tela Plana 32 4k', '2000.00', '1', '2');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TV 40 SAMSUNG', 'TV Tela Plana 42 4k', '3000.00', '1', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TÊNIS NIKE', 'Tênis Nike Tri Fusion Run 40', '550.50', '2', '1');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);
INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES('TÊNIS ADIDAS', 'Tênis Adidas Run Ultraboost', '750.00', '2', '3');
INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);

UPDATE estoque SET quantidade=10, qtd_minima=2, qtd_maxima=100, situacao='ATIVO' WHERE id_produto = 1;
UPDATE estoque SET quantidade=5, qtd_minima=2, qtd_maxima=150, situacao='INATIVO' WHERE id_produto = 2;
UPDATE estoque SET quantidade=100, qtd_minima=2, qtd_maxima=500, situacao='BLOQUEADO' WHERE id_produto = 3;
UPDATE estoque SET quantidade=300, qtd_minima=50, qtd_maxima=1000, situacao='ATIVO' WHERE id_produto = 4;

/* POR SE TRATAR DE UMA RELAÇÃO 1:1, UMA TENTATIVA DE INSERÇÃO DE UM NOVO ESTOQUE PARA UM PRODUTO DARÁ ERRO */
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (1, 20, 2, 100, 'ATIVO');*/
/* A TENTATIVA DE INSERIR UM ESTOQUE SEM QUE O PRODUTO EXISTA TAMBÉM CAUSARÁ ERRO*/
/*INSERT INTO estoque(id_produto, quantidade, qtd_minima, qtd_maxima, situacao) VALUES (4, 20, 2, 100, 'ATIVO');*/
/*DELETE FROM produto WHERE id = 4;*/

INSERT INTO venda(data, total, pago, taxa_desconto, empresa, situacao, id_cliente) VALUES('2022-09-29', '5000.00', false, '0.00', 'BIG STORE', 'FINALIZADA', '1');
INSERT INTO item_de_venda(quantidade, valor, id_produto, id_venda) VALUES('1', '2000.00', '1', '1');
INSERT INTO item_de_venda(quantidade, valor, id_produto, id_venda) VALUES('1', '3000.00', '2', '1');

INSERT INTO venda(data, total, pago, taxa_desconto, empresa, situacao, id_cliente) VALUES('2022-10-30', '1300.50', true, '0.00', 'BIG STORE', 'FINALIZADA', '2');
INSERT INTO item_de_venda(quantidade, valor, id_produto, id_venda) VALUES('1', '550.50' , '3', '2');
INSERT INTO item_de_venda(quantidade, valor, id_produto, id_venda) VALUES('1', '750.00' , '4', '2');
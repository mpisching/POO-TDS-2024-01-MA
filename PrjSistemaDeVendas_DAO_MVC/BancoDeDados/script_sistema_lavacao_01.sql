CREATE DATABASE IF NOT EXISTS db_lavacao;
USE db_lavacao;

CREATE TABLE marca(
   id int NOT NULL auto_increment,
   nome  varchar(50) NOT NULL,
   CONSTRAINT pk_marca
      PRIMARY KEY(id)
) engine = InnoDB;

CREATE TABLE cor(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   CONSTRAINT pk_cor
      PRIMARY KEY(id)
) engine = InnoDB;

CREATE TABLE servico(
   id int NOT NULL auto_increment,
   descricao varchar(200),
   valor decimal(10,2) NOT NULL,
   pontos int,
   CONSTRAINT pk_servico
      PRIMARY KEY(id)
) engine = InnoDB;

INSERT INTO marca(nome) VALUES('Ford');
INSERT INTO marca(nome) VALUES('Ferrari');
INSERT INTO marca(nome) VALUES('Audi');
INSERT INTO marca(nome) VALUES('Fiat');

INSERT INTO cor(nome) VALUES('Azul');
INSERT INTO cor(nome) VALUES('Preto');
INSERT INTO cor(nome) VALUES('Branco');

INSERT INTO servico(descricao, valor, pontos) VALUES ('Lavação Completa', 70.0, 10);
INSERT INTO servico(descricao, valor, pontos) VALUES ('Polimento', 50.0, 10);
INSERT INTO servico(descricao, valor, pontos) VALUES ('Lavação Motor', 100.0, 10);
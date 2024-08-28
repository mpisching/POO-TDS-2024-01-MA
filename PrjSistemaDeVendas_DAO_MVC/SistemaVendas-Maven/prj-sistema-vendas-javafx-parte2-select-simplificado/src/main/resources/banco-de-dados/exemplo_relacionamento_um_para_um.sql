/*RELACIONAMENTO 1:1*/
CREATE DATABASE IF NOT EXISTS db_teste_relacionamentos;
USE db_teste_relacionamentos;

CREATE TABLE cliente(
	id_cliente int NOT NULL auto_increment,
    nome VARCHAR(50) NOT NULL,
    sexo CHAR(1) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT ch_cliente CHECK (sexo in('F', 'M'))
);

CREATE TABLE documento(
	id_cliente int REFERENCES cliente (id_cliente),
    tipo VARCHAR(25) NOT NULL,
	numero VARCHAR(15) NOT NULL,
    CONSTRAINT pk_documento PRIMARY KEY (id_cliente),
    CONSTRAINT fk_documento_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

INSERT INTO cliente (nome, sexo) VALUES('JOSE', 'M');
INSERT INTO cliente (nome, sexo) VALUES('MARIA', 'F');
INSERT INTO cliente (nome, sexo) VALUES('PEDRO', 'M');
INSERT INTO cliente (nome, sexo) VALUES('LUCIA', 'F');

INSERT INTO documento (id_cliente, tipo, numero) VALUES(1, 'RG', '123.321-9');
INSERT INTO documento (id_cliente, tipo, numero) VALUES(2, 'CPF', '123.321.333-93');
INSERT INTO documento (id_cliente, tipo, numero) VALUES(3, 'RG', '333.222-1');
INSERT INTO documento (id_cliente, tipo, numero) VALUES(4, 'CPF', '333.333.222-12');

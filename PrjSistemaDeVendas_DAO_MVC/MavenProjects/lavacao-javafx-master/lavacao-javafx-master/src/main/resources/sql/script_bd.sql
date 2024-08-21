
# mysql

CREATE TABLE categorias(
   cdCategoria int not null auto_increment,
   descricao  varchar(50) NOT NULL,
   CONSTRAINT pk_categorias
      PRIMARY KEY(cdCategoria)
);

CREATE TABLE produtos(
   cdProduto int not null auto_increment,
   nome varchar(50) NOT NULL,
   preco decimal(10,2)     NOT NULL,
   quantidade int     NOT NULL,
   cdCategoria int     NOT NULL,
   CONSTRAINT pk_produtos
      PRIMARY KEY(cdProduto),
   CONSTRAINT fk_produtos_categorias
      FOREIGN KEY(cdCategoria)
      REFERENCES categorias(cdCategoria)
);

CREATE TABLE clientes(
   cdCliente int not null auto_increment,
   nome varchar(50) NOT NULL,
   cpf varchar(50) NOT NULL,
   telefone varchar(50) NOT NULL,
   CONSTRAINT pk_clientes
      PRIMARY KEY(cdCliente)
);

CREATE TABLE vendas(
   cdVenda int not null auto_increment,
   data date NOT NULL,
   valor decimal(10,2) NOT NULL,
   pago boolean NOT NULL,
   cdCliente int,
   CONSTRAINT pk_vendas
      PRIMARY KEY(cdVenda),
   CONSTRAINT fk_vendas_clientes
      FOREIGN KEY(cdCliente)
      REFERENCES clientes(cdCliente)
);

CREATE TABLE itensDeVenda(
   cdItemDeVenda int not null auto_increment,
   quantidade int NOT NULL,
   valor decimal(10,2) NOT NULL,
   cdProduto int,
   cdVenda int,
   CONSTRAINT pk_itensdevenda
      PRIMARY KEY(cdItemDeVenda),
   CONSTRAINT fk_itensdevenda_produtos
      FOREIGN KEY(cdProduto)
      REFERENCES produtos(cdProduto),
   CONSTRAINT fk_itensdevenda_vendas
      FOREIGN KEY(cdVenda)
      REFERENCES vendas(cdVenda)
);

INSERT INTO clientes(nome, cpf, telefone) VALUES('Rafael','111.111.111-11','(11) 1111-1111');
INSERT INTO clientes(nome, cpf, telefone) VALUES('João'  ,'222.222.222-22','(22) 2222-2222');
INSERT INTO clientes(nome, cpf, telefone) VALUES('Maria' ,'333.333.333-33','(33) 3333-3333');

INSERT INTO categorias(descricao) VALUES('Eletrônicos');
INSERT INTO categorias(descricao) VALUES('Vestuário');

INSERT INTO produtos(nome, preco, quantidade, cdCategoria) VALUES('TV 32 Sony', '2000.00', '10', '1');
INSERT INTO produtos(nome, preco, quantidade, cdCategoria) VALUES('TV 40 Sony', '3000.00', '10', '1');
INSERT INTO produtos(nome, preco, quantidade, cdCategoria) VALUES('Tênis Nike Tri Fusion Run 40', '550.00', '10', '2');
INSERT INTO produtos(nome, preco, quantidade, cdCategoria) VALUES('Tênis Adidas Galaxy Trainer 36', '215.00', '10', '2');

INSERT INTO vendas(data, valor, pago, cdCliente) VALUES('2016/04/30', '5000.00', false, '1');
INSERT INTO vendas(data, valor, pago, cdCliente) VALUES('2016/04/01', '765.00' , false, '1');

INSERT INTO itensDeVenda(quantidade, valor, cdProduto, cdVenda) VALUES('1', '2000.00', '1', '1');
INSERT INTO itensDeVenda(quantidade, valor, cdProduto, cdVenda) VALUES('1', '3000.00', '2', '1');
INSERT INTO itensDeVenda(quantidade, valor, cdProduto, cdVenda) VALUES('1', '550.00' , '3', '2');
INSERT INTO itensDeVenda(quantidade, valor, cdProduto, cdVenda) VALUES('1', '215.00' , '4', '2');
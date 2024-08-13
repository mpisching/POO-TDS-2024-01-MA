package br.edu.ifsc.fln.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    
    private Categoria categoria;
//    private int id_categoria.; ISSO É UM CRIME CONTRA A POO
    
    private Estoque estoque; //implementação do conceito de COMPOSIÇÃO - requer que o objeto seja construído pelo seu construtor, ou durante a declaração da variável
    // ou private Estoque estoque = new Estoque(); //como o Estoque é definido pela composição, também não é recomendado o método set para este atributo

    public Produto() {
        //qualquer construtor de Produto vai passar por este método para a a criação de estoque
        this.createEstoque();
    }

    public Produto(int id, String nome, String descricao, BigDecimal preco) {
        this();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        //this.createEstoque();
    }
    
    public Produto(int id, String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this(id, nome, descricao, preco);
        this.categoria = categoria;
        //this.createEstoque();
    }

    private void createEstoque() {
        //associação bidirecional - define o estoque do produto
        this.estoque = new Estoque();
        //atribui o produto ao estoque
        this.estoque.setProduto(this);
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Estoque getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}

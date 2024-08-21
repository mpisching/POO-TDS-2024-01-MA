/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mpisc
 */
public abstract class Fornecedor extends Object {
    protected int id;
    protected String nome;
    protected String email;
    protected String fone;
    
    protected List<Produto> produtos;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
    public List<Produto> getProdutos() {
        return this.produtos;
    }
    
    public void add(Produto produto) {
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        produtos.add(produto);
        produto.setFornecedor(this);
    }
    
    public void remove(Produto produto) {
        if (produtos != null) {
            produtos.remove(produto);
            produto.setFornecedor(null);
        }
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        return this.id == other.id;
    }
    
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados do fornecedor ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Id........: ").append(id).append("\n");
        sb.append("Nome......: ").append(nome).append("\n");
        sb.append("Fone......: ").append(fone).append("\n");
        sb.append("Email.....: ").append(email).append("\n");
        return sb.toString();
    }


}

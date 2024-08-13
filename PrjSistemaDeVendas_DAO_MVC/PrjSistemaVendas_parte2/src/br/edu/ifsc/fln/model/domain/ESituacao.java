/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author mpisc
 */
public enum ESituacao {
    //ATIVO, INATIVO, BLOQUEADO;
    ATIVO(1, "Ativo", "Active"), INATIVO(2, "Inativo", "Inactive"), BLOQUEADO(3, "Bloqueado", "Blocked");
    private int id;
    private String descricao;
    private String description;
    
    private ESituacao(int id, String descricao, String description) {
        this.id = id;
        this.descricao = descricao;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDescription() {
        return description;
    }
    
    
}

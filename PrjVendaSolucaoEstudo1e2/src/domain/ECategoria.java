/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public enum ECategoria {
    PEQUENO("Pequeno", "Small"), MEDIO("Médio", "Medium"), GRANDE("Grande", "Large"), MOTO("Moto", "Motorcycle"), PADRAO("Padrao", "Default");
    
    public String descricao;
    public String description;
    
    private ECategoria(String descricao, String description) {
        this.descricao = descricao;
        this.description = description;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDescription() {
        return description;
    }
    
    
}

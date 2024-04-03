/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Internacional extends Fornecedor {
    private String nif;
    private String pais;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Internacional() {
    }

    public Internacional(String nif, String pais) {
        this.nif = nif;
        this.pais = pais;
    }

    public Internacional(String nif, String pais, int id, String nome, String email, String fone) {
        super(id, nome, email, fone);
        this.nif = nif;
        this.pais = pais;
    }
    
    
}

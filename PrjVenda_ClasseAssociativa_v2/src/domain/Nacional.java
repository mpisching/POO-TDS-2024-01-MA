/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Nacional extends Fornecedor {
    private String cnpj;

    public Nacional() {
    }
    
    public Nacional(String cnpj) {
        this.cnpj = cnpj;
    }

    public Nacional(String cnpj, int id, String nome, String email, String fone) {
        super(id, nome, email, fone);
        this.cnpj = cnpj;
    }
    
    

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        //return "Nacional{" + "cnpj=" + cnpj + ", nome=" + nome + ", fone=" + fone + '}';
        return super.toString() + " Nacional{" + "cnpj=" + cnpj + '}';
    }

    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getDados());
        sb.append("CNPJ.......: ").append(cnpj).append("\n");
        return sb.toString();
    }

    @Override
    public String getDados(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append(getDados()).append("\n").append(msg);
        return sb.toString();
    }

    @Override
    public String getInfo() {
        return cnpj;
    }
    
    
    
    
}

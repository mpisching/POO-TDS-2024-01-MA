package ifsc.poo.lavacao.model.domain;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int cdCliente;
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(){
    }
    
    public Cliente(int cdCliente, String nome, String cpf) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}

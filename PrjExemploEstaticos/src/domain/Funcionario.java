/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Funcionario {
    private int matricula;
    private String nome;
    private double salario;
    private static double auxilioAlimentacao;

//    public Funcionario(int matricula, String nome, double salario, double auxilioAlimentacao) {
//        this.matricula = matricula;
//        this.nome = nome;
//        this.salario = salario;
//        Funcionario.auxilioAlimentacao = auxilioAlimentacao;
//    }

    public Funcionario(int matricula, String nome, double salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.salario = salario;
    }
    
    

    public Funcionario() {
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public static double getAuxilioAlimentacao() {
        return auxilioAlimentacao;
    }

    public static void setAuxilioAlimentacao(double auxilioAlimentacao) {
        Funcionario.auxilioAlimentacao = auxilioAlimentacao;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "matricula=" + matricula + ", nome=" + nome + ", salario=" + salario + ", auxilioAlimentacao=" + auxilioAlimentacao + '}';
    }
    
    
}

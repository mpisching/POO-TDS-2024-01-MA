/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mpisc
 */
public class IMCCalulator {
    private String nome;
    private float peso;
    private float altura;

    public IMCCalulator(String nome, float peso, float altura) {
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    
    
    public float calcularImc() {
        //return (float)(peso / Math.pow(altura, 2));
        return peso / (altura * altura);
    }
    
    public String verificarClassificacao(float imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25.0) {
            return "Normal";
        } else if (imc < 30.0) {
            return "Sobrepeso";
        } else if (imc < 40.0) {
            return "Obesidade";
        } else {
            return "Obesidade grave";
        }        
    }   

    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome.........: ").append(nome).append("\n");
        sb.append("Peso.........: ").append(peso).append("\n");
        sb.append("Altura.......: ").append(altura).append("\n");
        sb.append("IMC..........: ").append(calcularImc()).append("\n");
        sb.append("Situação.....: ").append("\n").append(verificarClassificacao(calcularImc()));
        return sb.toString();
    }
}

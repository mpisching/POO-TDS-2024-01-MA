/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mpisc
 */
public class Imc {

    private String nome;
    private String sexo;
    private int idade;
    private float peso;
    private float altura;
//    private float imc;
//    private String classificao;

    public Imc() {
    }

    public Imc(String nome, String sexo, int idade, float peso, float altura) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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

    public float calcularIMC() {
        return (float) (peso / (Math.pow(altura, 2)));
    }

    public String classificarIMC(float imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade";
        } else {
            return "Obesidade Grave";
        }
    }
    
    public String classificarIMC() {
        float imc = calcularIMC();
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade";
        } else {
            return "Obesidade Grave";
        }
    } 
    
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** Dados do IMC calculado ****").append("\n");
        sb.append("================================").append("\n");
        sb.append("Nome....: ").append(nome).append("\n");
        sb.append("Idade...: ").append(idade).append("\n");
        sb.append("Sexo....: ").append(sexo).append("\n");
        sb.append("Altura..: ").append(altura).append("\n");
        sb.append("Peso....: ").append(peso).append("\n\n");
        sb.append("IMC.....: ").append(calcularIMC()).append("\n");
        sb.append("Classe..: ").append(classificarIMC()).append("\n");
        sb.append("===============================").append("\n");
        return sb.toString();
    }

}

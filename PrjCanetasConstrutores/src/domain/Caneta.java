/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class Caneta {
    private String marca;
    private String modelo;
    private String cor;
    private int qtdCarga;

    public Caneta(String marca) {
        this.marca = marca;
    }
    
    public Caneta(String marca, String modelo) {
        this(marca);
        this.modelo = modelo;
    }
    
    public Caneta(String marca, String modelo, String cor) {
//        this.marca = marca;
//        this.modelo = modelo;
        this(marca, modelo);
        this.cor = cor;
    }
    
    public Caneta(String marca, String modelo, String cor, int qtd) 
            throws Exception {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.reporCarga(qtd);
    }
   
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public boolean temCarga() {
        return qtdCarga > 0 ? true : false;
    }
    
    public void escrever(String msg) {
        qtdCarga--;
        System.out.println("escrevendo: " + msg);
    }
    
    public void desenhar(String figura) {
        qtdCarga-=2;
        System.out.println(this.getClass().getSimpleName() + " desenhando... " + figura);
    }
    
    /**
     * Método para executar uma pintura...
     */
    public void pintar() {
        qtdCarga-=3;
        System.out.println("pintando...");
    }

    @Override
    public String toString() {
        return "Caneta{" + "marca=" + marca + ", modelo=" + modelo + ", cor=" + cor + ", qtdCarga=" + qtdCarga + '}';
    }
    
    /**
     * Repoem uma determinada quantidade de carga
     * @param qtd - valor int que corresponde ....
     * @return boolean - true(sucesso) false (carga cheia)
     */
    public boolean reporCarga(int qtd) throws Exception {
        if (qtdCarga + qtd > 100) {
            throw new Exception("Carga cheia");
            //return false;
        } else {
            qtdCarga += qtd;
            return true;
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQtdCarga() {
        return qtdCarga;
    }



    
}

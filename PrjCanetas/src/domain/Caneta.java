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
    public String marca;
    public String modelo;
    public String cor;
    public int qtdCarga = 100;
    
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
    
    public void pintar() {
        qtdCarga-=3;
        System.out.println("pintando...");
    }

    @Override
    public String toString() {
        return "Caneta{" + "marca=" + marca + ", modelo=" + modelo + ", cor=" + cor + ", qtdCarga=" + qtdCarga + '}';
    }

    
}

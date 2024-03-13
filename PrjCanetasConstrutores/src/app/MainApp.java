/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Caneta;

/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        Caneta c1 = new Caneta("BIC");
        Caneta c2 = new Caneta("Faber CAstell", "Marcador");
        Caneta c3 = new Caneta("Faber CAstell", "Marcador", "Azul");
        Caneta c4 = null;
        try {
            c4 = new Caneta("Faber CAstell", "Marcador", "Azul", 120);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        //c1.setQtdCarga(150);
//        
        c1.setCor("Vermelha");
        c1.setModelo("marcador");
        
        System.out.println("Caneta c1: " + c1);
        System.out.println("Caneta c4: " + c4);
        
    }
}

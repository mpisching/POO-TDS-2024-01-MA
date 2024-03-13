/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainapp;

import domain.Produto;
import java.math.BigDecimal;

/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        Produto p1 = 
            new Produto(1, "Arroz", "Arroz Parboilizado", new BigDecimal(5.0));
        Produto p2 =
            new Produto(1, "Açucar", "Açucar Mascavo", new BigDecimal(4.50));
        
        print(p1);
        print(p2);
    }
    
    public static void print(Produto p) {
        System.out.println("Produto: " + p);
    }
    
}

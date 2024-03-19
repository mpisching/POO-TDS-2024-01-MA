/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        Produto p1 = new Produto();
        p1.setId(1);
        p1.setNome("Bermuda");
        p1.setDescricao("Bermuda de banho");
        p1.setDescricaoCategoria("Vestuário");
        p1.setPreco(new BigDecimal(120.0));
        produtos.add(p1);
        
        Produto p2 = new Produto();
        p2.setId(2);
        p2.setNome("Tenis");
        p2.setDescricao("Tenis Runner");
        p2.setDescricaoCategoria("Calcado");
        p2.setPreco(new BigDecimal(320.0));
        produtos.add(p2);
        
        Produto p3 = new Produto();
        p3.setId(3);
        p3.setNome("Camisa");
        p3.setDescricao("Camisa Polo");
        p3.setDescricaoCategoria("Vestiario");
        p3.setPreco(new BigDecimal(250.0));
        produtos.add(p3);
        
        Produto p4 = 
            new Produto(4, "Camiseta", "Regata", "Vestuario", new BigDecimal(85.0));
        produtos.add(p4);
        Produto p5 = 
            new Produto(5, "Sapato", "Sapato Confort", "calçado", new BigDecimal(235.0));
        produtos.add(p5);
        
//        System.out.println("Produto: " + p1);
//        System.out.println("Produto: " + p2);
//        System.out.println("Produto: " + p3);
//        System.out.println("Proudto: " + p4);
//        System.out.println("Produto: " + p5);

        print(produtos);
    }

    private static void print(List<Produto> produtos) {
        for (Produto p: produtos) {
            System.out.println("Produto: " + p);
        }
    }
}

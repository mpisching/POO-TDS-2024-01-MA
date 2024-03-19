/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
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
        
        Categoria c1 = new Categoria();
        c1.setId(1);
        c1.setDescricao("Vestuario");
        System.out.println("Categoria: " + c1);
        
        Categoria c2 = new Categoria();
        c2.setId(2);
        c2.setDescricao("Calçado");
        System.out.println("Categoria: " + c2);
        
        Produto p1 = new Produto();
        p1.setId(1);
        p1.setNome("Bermuda");
        p1.setDescricao("Bermuda de banho");
        //p1.setDescricaoCategoria("Vestuário");
        p1.setPreco(new BigDecimal(120.0));
        p1.setCategoria(c1);
        produtos.add(p1);
        
        Produto p2 = new Produto();
        p2.setId(2);
        p2.setNome("Tenis");
        p2.setDescricao("Tenis Runner");
        //p2.setDescricaoCategoria("Calcado");
        p2.setPreco(new BigDecimal(320.0));
        p2.setCategoria(c2);
        produtos.add(p2);
        
        Produto p3 = new Produto();
        p3.setId(3);
        p3.setNome("Camisa");
        p3.setDescricao("Camisa Polo");
        //p3.setDescricaoCategoria("Perfumaria");
        p3.setPreco(new BigDecimal(250.0));
        p3.setCategoria(c1);
        produtos.add(p3);
        
        Produto p4 = 
            new Produto(4, "Camiseta", "Regata", new BigDecimal(85.0), c1);
        p4.setCategoria(c1);
        produtos.add(p4);
        Produto p5 = 
            new Produto(5, "Sapato", "Sapato Confort", new BigDecimal(235.0));
        p5.setCategoria(c2);
        produtos.add(p5);
        
        Categoria c3 = new Categoria(3 ,"Perfumaria");
        Produto p6 = new Produto(c3);
        
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
            System.out.println("Categoria: " + p.getCategoria().getDescricao());
        }
    }
}

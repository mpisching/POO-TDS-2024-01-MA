/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
import domain.Estoque;
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
        Categoria c1 = new Categoria();
        c1.setId(1);
        c1.setDescricao("Vestuario");
        System.out.println("Categoria: " + c1);
        
        Produto p1 = new Produto();
        p1.setId(1);
        p1.setNome("Bermuda");
        p1.setDescricao("Bermuda de banho");
        p1.setPreco(new BigDecimal(120.0));
        p1.setCategoria(c1);
        
        p1.getEstoque().repor(50);
        p1.getEstoque().setQtdMaxima(100);
        p1.getEstoque().setQtdMinima(10);
        p1.getEstoque().retirar(20);
        
        
        /*
        Na composição o objeto estoque vai ser criado ao instanciar um objeto produto
        */
//        Estoque e = new Estoque();
//        e.setQtdMaxima(100);
//        e.setQtdMinima(10);
//        e.setQuantidade(50);
        //p1.setEstoque(e);
        
        print(p1);
        p1 = null;
        System.out.println("Produto: " + p1);
        System.out.println("Categoria: " + c1);
        System.out.println("Estoque: " + p1.getEstoque().getQuantidade());
    }

    private static void print(Produto produto) {
        System.out.println("Produto...: " + produto);
        System.out.println("Categoria.: " + produto.getCategoria().getDescricao());
        System.out.println("Estoque...: " + produto.getEstoque().getQuantidade());
        System.out.println("Máxima....: " + produto.getEstoque().getQtdMaxima());
        System.out.println("Minima....: " + produto.getEstoque().getQtdMinima());
    }
}

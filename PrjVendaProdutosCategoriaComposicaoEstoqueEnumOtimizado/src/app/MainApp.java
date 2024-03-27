/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
import domain.ESituacao;
import domain.Estoque;
import domain.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        
        p1.getEstoque().setSituacao(ESituacao.ATIVO);
        try {
            p1.getEstoque().repor(50);
        } catch (Exception ex) {
            System.out.println("Falha na operacao: \n" + ex.getMessage());
        }
        p1.getEstoque().setQtdMaxima(100);
        p1.getEstoque().setQtdMinima(10);
        p1.getEstoque().retirar(20);
        print(p1);
        
        Produto p2 = new Produto(2, "Camisa", "Camisa do Grêmio", 
                new BigDecimal(400.0), c1);
        p2.getEstoque().setSituacao(ESituacao.ATIVO);
        p2.getEstoque().setQtdMaxima(100);
        print(p2);
        /*
            levando o problema a seguir para a classe Estoque - como uma regra de negocio
        */
//        if (p2.getEstoque().getSituacao() == ESituacao.ATIVO) {
//            p2.getEstoque().repor(30);
//        } else {
//            System.out.println(
//                    "A situacao do produto e " 
//                            + p2.getEstoque().getSituacao().getDescricao());
//        }
        try {
            p2.getEstoque().repor(30);
        } catch (Exception ex) {
            System.out.println("Falha na operacao: \n" + ex.getMessage());
        }
        System.out.println("Estoque de p2: " + p2.getEstoque().getQuantidade());
        p2.getEstoque().retirar(10);
        System.out.println("Estoque de p2: " + p2.getEstoque().getQuantidade());
        
        
    }

    private static void print(Produto produto) {
        System.out.println("Produto...: " + produto);
        System.out.println("Categoria.: " + produto.getCategoria().getDescricao());
        System.out.println("Situacao..: " + 
                produto.getEstoque().getSituacao().getDescricao());//"Ativo"
        System.out.println("Estoque...: " + produto.getEstoque().getQuantidade());
        System.out.println("Máxima....: " + produto.getEstoque().getQtdMaxima());
        System.out.println("Minima....: " + produto.getEstoque().getQtdMinima());
    }
}

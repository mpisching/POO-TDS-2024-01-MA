/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
import domain.Fornecedor;
import domain.Internacional;
import domain.Nacional;
import domain.Produto;
import java.math.BigDecimal;


/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {

        Nacional fornecedorNacional = new Nacional("88888888893943", 2, 
                "Positivo", "contato@positivo.com.br", "23423242");
//        print(fornecedorNacional);
//        Fornecedor fornecedor = new Nacional("1234567890", 4, 
//                "Needs", "contato@needs.com.br", "373737373737");
//        print(fornecedor);
//
//        Internacional fornecedorInternacional = 
//                new Internacional("2342", "Espanha", 1, "Gluon", 
//                        "gluon@gluon.com.es", "999999999");
//        print(fornecedorInternacional);
//        fornecedor = new Internacional("423414", "Paraguai", 5, 
//                "graos para", "contato@graospara.com.pr", "45345345");
//        print(fornecedor, "Parabens Paraguai!!!!");
        
        Categoria categoria1 = new Categoria(1, "Vestuario");
        Categoria categoria2 = new Categoria(2, "Calcado");
        Produto p1 = new Produto(1, "Tenis", "Tenis Runner", 
                new BigDecimal(250.0), categoria2);
        fornecedorNacional.add(p1);
        Produto p2 = new Produto(2, "Sapato", "Sapato Confort", 
                new BigDecimal(300.0), categoria2);
        fornecedorNacional.add(p2);
        Produto p3 = new Produto(3, "Camisa", "Camisa Slim", 
                new BigDecimal(250.0), categoria1);
        fornecedorNacional.add(p3);
        print(fornecedorNacional, true);
    }

    public static void print(Fornecedor fornecedor, boolean comProduto) {
        System.out.println(fornecedor.getDados());
        if (comProduto) {
            System.out.println(fornecedor.getDetalhesProdutos());
        }
    }
    
    public static void print(Fornecedor fornecedor) {
        System.out.println(fornecedor.getDados());
    }

    public static void print(Fornecedor fornecedor, String msg) {
        System.out.println(fornecedor.getDados(msg));
    }

}

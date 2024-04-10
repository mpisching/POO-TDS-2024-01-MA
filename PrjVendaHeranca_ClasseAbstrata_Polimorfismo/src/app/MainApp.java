/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
import domain.ESituacao;
import domain.Estoque;
import domain.Fornecedor;
import domain.Internacional;
import domain.Nacional;
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

        Nacional fornecedorNacional = new Nacional("88888888893943", 2, 
                "Positivo", "contato@positivo.com.br", "23423242");
        print(fornecedorNacional);
        printResumido(fornecedorNacional);
        Fornecedor fornecedor = new Nacional("1234567890", 4, 
                "Needs", "contato@needs.com.br", "373737373737");
        print(fornecedor);
        printResumido(fornecedor);

        Internacional fornecedorInternacional = 
                new Internacional("2342", "Espanha", 1, "Gluon", 
                        "gluon@gluon.com.es", "999999999");
        print(fornecedorInternacional);
        printResumido(fornecedorInternacional);
        fornecedor = new Internacional("423414", "Paraguai", 5, 
                "graos para", "contato@graospara.com.pr", "45345345");
        print(fornecedor);
        printResumido(fornecedor);
    }
    
//    public static void print(Nacional fornecedor) {
//        System.out.println("Fornecedor Nacional: " + fornecedor.toString());
//    }
//    
//    public static void print(Internacional fornecedor) {
//        System.out.println("Fornecedor Internacional: " + fornecedor);
//    }

    public static void print(Fornecedor fornecedor) {
        System.out.println("Fornecedor: " + fornecedor.toString());
    }

    private static void printResumido(Fornecedor fornecedor) {
        //mostrar nome e cnpj quando for Nacional
        //mostrar nome, nif e pais quando for Internacional
        System.out.println("Nome.....: " + fornecedor.getNome());
        if (fornecedor instanceof Nacional) {
            Nacional nacional = (Nacional)fornecedor;
            System.out.println("CNPJ.....: " + 
                    nacional.getCnpj());
        } else {
            System.out.println("NIF......: " + 
                    ((Internacional)fornecedor).getNif());
            System.out.println("Pais.....: " + 
                    ((Internacional)fornecedor).getPais());
        }
        
    }

//    private static void printResumido(Internacional fornecedorInternacional) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

}

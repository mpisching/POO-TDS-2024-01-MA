/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.test;

import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.Fornecedor;
import br.edu.ifsc.fln.model.domain.Internacional;
import br.edu.ifsc.fln.model.domain.Nacional;
import br.edu.ifsc.fln.model.domain.Produto;

/**
 *
 * @author mpisc
 */
public class TestMain {
    public static void main(String[] args) {
        int y = 10;
        int x = 0;
        int r = 0;
        try {
            r = y / x;
        } catch (ArithmeticException ex) {
            System.out.println("Impossivel dividir um valor por zero...");
            ex.getMessage();
            ex.printStackTrace();
        }
        System.out.println("R: " + r);
        
        int[] v = new int[5];
        for (int i = 0; i <= 5; i++) {
            try {
                v[i] = 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("O indice está fora dos limites do vetor");
            } catch (Exception e) {
                System.out.println("Ocorreu uma falha...");
            }
        }
        
//        ESituacao situacao = ESituacao.ATIVO;
//        System.out.println("Situacao: " + situacao.toString());

        //Nacional nacional = new Nacional();
//        Fornecedor nacional = new Nacional();
//        nacional.setId(1);
//        ((Nacional)nacional).setCnpj("123.321.1233-22");
//        nacional.setNome("Asics do Brasil");
//        nacional.setEmail("asics@asics.com.br");
//        nacional.setFone("2222-2222");
//        
//        Fornecedor nacional1 = new Nacional();
//        nacional1.setId(1);
//        ((Nacional)nacional1).setCnpj("123.321.1233-22");
//        nacional1.setNome("Asics do Brasil");
//        nacional1.setEmail("asics@asics.com.br");
//        nacional1.setFone("2222-2222"); 
//        
//        if (nacional.equals(nacional1)) {
//            System.out.println("iguais");
//        } else {
//            System.out.println("diferentes");
//        }
//        
//        Internacional internacional = new Internacional();
//        internacional.setId(1);
//        internacional.setNif("657898-0");
//        internacional.setPais("Argentina");
//        internacional.setNome("Mendoza Wines");
//        internacional.setEmail("contact@mendozawines.com.ar");
//        internacional.setFone("3443-3433");
//
////        print((Nacional)nacional);
////        print(internacional);
//        
//        //implementando o método print aplicando o conceito de polimorfismo
//        print(nacional);
//        print(internacional);
        
    }
    
//    public static void print(Nacional nacional) {
//        System.out.println("**** Fornecedor Nacional ****");
//        System.out.println("Nome: " + nacional.getNome());
//        System.out.println("Fone: " + nacional.getFone());
//        System.out.println("CNPJ: " + nacional.getCnpj());
//    }
//    
//    public static void print(Internacional internacional) {
//        System.out.println("**** Fornecedor Internacional ****");
//        System.out.println("Nome....: " + internacional.getNome());
//        System.out.println("Fone....: " + internacional.getFone());
//        System.out.println("NIF.....: " + internacional.getNif());        
//        System.out.println("País....: " + internacional.getPais());
//    }
   
    //este método será substituido por um print que invoca o método getDados() --> nova versão
//    public static void print(Fornecedor fornecedor) {
//        //este método implementa o polimorfismo (várias formas), pelo fato de receber qualquer 
//        //instância de Fornecedor e executar comportamentos diferentes, de acordo com o tipo da instância.
//        System.out.printf("**** Dados do Fornecedor %s ****\n", fornecedor.getClass().getSimpleName());
//        System.out.println("Nome....: " + fornecedor.getNome());
//        System.out.println("Fone....: " + fornecedor.getFone());
//        System.out.println("Email...: " + fornecedor.getEmail());
//        if (fornecedor instanceof Nacional) {
//            System.out.println("CNPJ....: " + ((Nacional)fornecedor).getCnpj());
//        } else {
//            System.out.println("NIF.....: " + ((Internacional)fornecedor).getNif());
//            System.out.println("País....: " + ((Internacional)fornecedor).getPais());
//        }
//        
//        //IMPLEMENTAR O CONCEITO DE OVERRIDING - SUBSCRIÇÃO DE MÉTODOS (REESCRITA).
//        //FAZER UM EXEMPLO COM A IMPLEMENTAÇÃO DO MÉTODO getDados()
//    }
    
    public static void print(Fornecedor fornecedor) {
        System.out.println(fornecedor.getDados());
    }
}

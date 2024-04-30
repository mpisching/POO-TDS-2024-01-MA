/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Fornecedor;
import domain.Internacional;
import domain.Nacional;
import domain.Relatorio;


/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {

        Nacional fornecedorNacional = new Nacional("88888888893943", 2, 
                "Positivo", "contato@positivo.com.br", "23423242");
        print(fornecedorNacional);
        Fornecedor fornecedor = new Nacional("1234567890", 4, 
                "Needs", "contato@needs.com.br", "373737373737");
        print(fornecedor);

        Internacional fornecedorInternacional = 
                new Internacional("2342", "Espanha", 1, "Gluon", 
                        "gluon@gluon.com.es", "999999999");
        print(fornecedorInternacional);
//        fornecedor = new Internacional("423414", "Paraguai", 5, 
//                "graos para", "contato@graospara.com.pr", "45345345");
//        print(fornecedor, "Parabens Paraguai!!!!");
        
    }

    public static void print(Fornecedor fornecedor) {
        //System.out.println(fornecedor.getDados());
        System.out.println(Relatorio.imprimir(fornecedor));
    }

    public static void print(Fornecedor fornecedor, String msg) {
        System.out.println(fornecedor.getDados(msg));
    }

}

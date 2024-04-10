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

        //Fornecedor fornecedor = 
        //        new Fornecedor(1, "Toshiba", "contato@toshiba.com", "4899938983" );
        Nacional fornecedorNacional = new Nacional("88888888893943", 2, 
                "Positivo", "contato@positivo.com.br", "23423242");
//        Nacional n = new Nacional("1313", 3, "Sul Brasil", 
//                "contato@sulbrasil.com.br", "9999999");
        print(fornecedorNacional);

        Internacional fornecedorInternacional = 
                new Internacional("2342", "Espanha", 1, "Gluon", 
                        "gluon@gluon.com.esp", "999999999");
        print(fornecedorInternacional);
    }
    
    public static void print(Nacional fornecedor) {
        System.out.println("Fornecedor Nacional: " + fornecedor.toString());
    }
    
    public static void print(Internacional fornecedor) {
        System.out.println("Fornecedor Internacional: " + fornecedor);
    }

}

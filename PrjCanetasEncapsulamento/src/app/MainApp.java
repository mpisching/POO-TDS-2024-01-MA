/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Caneta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Caneta c1 = new Caneta();
        Caneta c2 = new Caneta();
        Caneta c3 = c2;
        Caneta c4; 
        
        System.out.print("Cor........:");
        c1.setCor(entrada.next());
        System.out.print("Marca......:");
        c1.setMarca(entrada.next());
        System.out.print("Modelo.....:");
        c1.setModelo(entrada.next());
        c1.desenhar("retangulo");
        c1.pintar();
        c1.escrever("A caneta c1 desenhou e pintou um retangulo. ");
        System.out.println("Quantidade de Carga: " + c1.getQtdCarga());
        if (c1.reporCarga(60)) {
            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Não foi possível fazer a recarga!!");
        }
        //c1.setQtdCarga(150);
//        
        
        c2.setCor("Vermelha");
        c2.setMarca("Bic");
        c2.setModelo("marcador");
        do {
            c2.desenhar("triangulo");
        } while (c2.temCarga());
//        c3.cor = "Preta";
//        c4 = new Caneta();
//        c4.cor = "verde";
//        c4.qtdCarga = 50;
//        Caneta c5 = new Caneta();//instanciação de classe
//        c5.marca = "Pentel";
        
        System.out.println("Caneta c1: " + c1);
        System.out.println("Caneta c2: " + c2);
//        System.out.println("Caneta c3: " + c3);
//        System.out.println("Caneta c4: " + c4);
//        System.out.println("Caneta c5: " + c5);
        
        
    }
}

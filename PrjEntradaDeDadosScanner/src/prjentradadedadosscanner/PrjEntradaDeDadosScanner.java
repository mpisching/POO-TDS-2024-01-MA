/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prjentradadedadosscanner;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class PrjEntradaDeDadosScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner entrada = new Scanner(System.in, "ISO-8859-1");
        System.out.println("Entrada de dados - aulão");
        System.out.print("Nome.........: ");
        String nome = entrada.next();
        System.out.print("Idade........: ");
        int idade = entrada.nextInt();
        System.out.print("Altura.......: ");
        float altura = entrada.nextFloat();
        
        System.out.println("***** Dados de entrada *****");
        System.out.println("Nome.......: " + nome);
        System.out.println("Idade......: " + idade);
        System.out.println("Altura.....: " + df.format(altura));
           
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prjexemplometodos;

import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class PrjExemploMetodos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe 3 números inteiros...");
        int x = entrada.nextInt();
        int y = entrada.nextInt();
        int z = entrada.nextInt();
        double media = media(somar(x, somar(y, z)),3.0);
        imprimir(media);
    }
    
    public static int somar(int op1, int op2) {
        return op1 + op2;
    }
    
    public static double media(int n, double d) {
        return n / d;
    }
    
    public static void imprimir(double media) {
        System.out.println("Média: " + media);
    }
    
}

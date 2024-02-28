/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prjexemplovetor2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Professor
 */
public class PrjExemploVetor2 {
    private static final int TAM = 6;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor = new int[TAM];
        int x = 10;
        imprimir(vetor);
        inicializar(vetor);
        
        imprimir(x);//passagem de parâmetro por valor
        //imprimir(x);
        imprimir(vetor);//passagem de parâmetro por referência
        //imprimir(vetor);
        
        //novo vetor
        int[] notas = inicializar(100);
        System.out.println("notas...");
        imprimir(notas);
         
    }
    
    public static void inicializar(int[] v) {
        Random r = new Random();
        for (int i = 0; i < v.length; i++) {
            int valor = r.nextInt(600) + 1;
            if (!contains(valor, v)) {
                v[i] = valor;
            } else {
                i--;
            }
        }
    }
    
    public static int[] inicializar() {
        int[] vetor = new int[TAM];
        inicializar(vetor);
        return vetor;
    }
    
    public static int[] inicializar(int tam) {
        int[] vetor = new int[tam];
        inicializar(vetor);
        return vetor;
    }
    
    public static boolean contains(int valor, int[] v) {
        for (int elemento : v) {
            if (elemento == valor) {
                return true;
            } 
        }
        return false;
    }
    
    public static void imprimir(int[] v) {
       for (int i = 0; i < v.length; i++) {
           System.out.println("Vetor[" + i + "] = " + v[i]);
       }
       //v[3] = 55;
    }
    
    public static void imprimir(int x) {
        System.out.println("X = " + x);
        //x = 11;
    }
    
}

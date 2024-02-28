/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prjexemplovetor;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class PrjExemploVetor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Defina o tamanho do vetor: ");
        int tam = entrada.nextInt();
        int[] vetor = new int[tam];
        int[] vetor2 = {10,20,110,20,220};
        
        try {
            vetor[0] = 5;
            vetor[5] = 10;
            System.out.println("Informe um valor inteiro: ");
            vetor[3] = entrada.nextInt();
            System.out.println("Informe o índice do vetor: ");
            int i = entrada.nextInt();
            System.out.println("Informe um valor inteiro: ");
            vetor[i] = entrada.nextInt();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Indice de acesso fora "
                    + "dos limites do vetor!!!");
        }
        System.out.println("vetor " + Arrays.toString(vetor));
        
    }
    
}

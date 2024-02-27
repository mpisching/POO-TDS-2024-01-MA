/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exemplosrepeticao;

import java.util.Scanner;

/**
 *
 * @author Professor
 */
public class ExemplosRepeticao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Qual limite de x "
                + "para encontrar os números pares? ");
        int x = entrada.nextInt();
        int abs1;
        if (x < 0) {
            abs1 = -x;
        } else {
            abs1 = x;
        }
        
        int abs2 = (x < 0) ? -x : x;
        
        for (int i = 0; i < x ; i= i + 2) { //i = i + 2
//            if (i % 2 == 0) {
//                System.out.println("Pares: " + i);
//            }
            System.out.println("Pares: " + i);
        }
        
        //while - enquanto
//        int i = 0;
//        while (i < x) {
//            System.out.println("Pares: " + i);
//            i += 2;
//        }
        
        //do .. while
        //faça .. enquanto
//        int i = 0;
//        do {
//          System.out.println("Pares: " + i);
//          i += 2;  
//        } while (i < x);
        
        int opcao;
        do {
            System.out.println("1 - Credito");
            System.out.println("2 - Debito");
            System.out.println("3 - Pix");
            System.out.println("4 - Dinheiro");
            System.out.print("Opcao: ");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1: 
                    System.out.println("Lendo cartão de crédito...");
                    break;
                case 2:
                    System.out.println("Lendo cartão de débito...");
                    break;
                case 3: 
                    System.out.println("Fazendo a leitura do pix...");
                    break;
                case 4: 
                    System.out.println("Abrindo caixa...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao <= 0 || opcao > 4);
    
        
//        int x = 3, y = 7, z = 3;
//        
//        if (x > y); {
//            System.out.println("x é maior");
//        }
//    
//        float m = (float)((x + y + z) / 3.0);
//        System.out.println("Media: " + m);
    }
    
    
}

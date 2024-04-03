/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucaoexe15listapreliminar;

import java.util.Scanner;

/**
 *
 * @author mpisching
 */
public class SolucaoExe15ListaPreliminar {

    private static int[] assentos = new int[10];
    private static Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcao = 0;
        do {
            System.out.println("1 - Primeira classe");
            System.out.println("2 - Classe executiva");
            System.out.println("3 - Listar situação dos assentos");
            System.out.println("4 - Inicializar o voo");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1: 
                    venderPassagemPrimeiraClasse();
                    break;
                case 2: 
                    venderPassagemClasseExecutiva();
                    break;
                case 3: 
                    imprimir();
                    break;
                case 4: 
                    inicializarAssentos();
                    break;
                case 0: 
                    System.out.println("O sistema de controle de vendas de passagens será finalizado");
                    break;
                default: System.out.println("Opção inválida!!!");
            }
        } while(opcao != 0);
    }
    
    private static void venderPassagemClasseExecutiva() {
        int assento = verificarDisponibilidadeClasseExecutiva();
        
        if (assento == -1) {
            System.out.println("A classe executiva está lotada.");
            int assentoDisponivelPC = verificarDisponibilidadePrimeiraClasse();
            if (assentoDisponivelPC == -1) {
                System.out.println("Voo está lotado, aguarde o próximo!!!");
            } else {
                System.out.print("Gostaria de ocupar um assento na primeira classe (S/N)?");
                char opcao = entrada.next().charAt(0);
                if (opcao == 's' || opcao == 'S') {
                    assentos[assentoDisponivelPC] = 1;
                    imprimirTicket(assentoDisponivelPC, "Primeira Classe");
                }
            }            
        } else {
            assentos[assento] = 1;
            imprimirTicket(assento, "Classe Executiva");
        }
    }
    
    private static int verificarDisponibilidadeClasseExecutiva() {
        for (int i = 5; i < 10; i++) {
            if (assentos[i] == 0) {
                return i;
            }
        } 
        return -1;        
    }
    
    private static void venderPassagemPrimeiraClasse() {
        int assento = verificarDisponibilidadePrimeiraClasse();
        
        if (assento == -1) {
            System.out.println("A primeira classe está lotada.");
            int assentoDisponivelCE = verificarDisponibilidadeClasseExecutiva();
            if (assentoDisponivelCE == -1) {
                System.out.println("Voo está lotado, aguarde o próximo!!!");
            } else {
                System.out.print("Gostaria de ocupar um assento na classe Executiva (S/N)?");
                char opcao = entrada.next().charAt(0);
                if (opcao == 's' || opcao == 'S') {
                    assentos[assentoDisponivelCE] = 1;
                    imprimirTicket(assentoDisponivelCE, "Classe Executiva");
                }
            }
        } else {
            assentos[assento] = 1;
            imprimirTicket(assento, "Primeira classe");
        }
    }
    
    private static int verificarDisponibilidadePrimeiraClasse() {
        for (int i = 0; i < 5; i++) {
            if (assentos[i] == 0) {
                return i;
            }
        } 
        return -1;
    }
    
    private static void imprimir() {
        for (int i = 0; i < assentos.length; i++) {
            System.out.println("Assento " + (i + 1) + ": " +
                    (assentos[i] == 0 ? "Disponível" : "Ocupado"));
        }
    }
    
    private static void inicializarAssentos() {
        for (int i = 0; i < assentos.length; i++) {
            assentos[i] = 0;
        }
    }

    private static void imprimirTicket(int assento, String classe) {
        System.out.println("Você reservou o assento " + (assento + 1) + 
                " da " + classe);
    }
    
}

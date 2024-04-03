/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe03listapreliminar;

import java.util.Scanner;

/**
 *
 * @author mpisc
 */
public class SolucaoExe03ListaPreliminar {

    /**
     * 3.Faça um programa em Java que permite ler três valores para os lados de um triângulo, considerando lados como: A, B e C.Verificar se os lados fornecidos formam um realmente um triângulo, e se for esta condição verdadeira, deverá ser indicado qual tipo de triângulo foi formado: isósceles, escaleno ou equilátero. Para se estabelecer este algoritmo é necessário, em primeiro lugar, saber o que realmente é um triângulo. Se você não souber o que é um triângulo, consequentemente não conseguirá resolver o problema. Triângulo é uma forma geométrica (polígono) composta por três lados, onde cada lado é menor que a soma dos outros dois lados. Perceba que isto é uma regra (uma condição) e deverá ser considerada. É um triângulo quando A < B + C, quando B < A + C e quando C < A + B.
     * Tendo certeza de que os valores informados para os três lados formam um triângulo, serão então analisados os valores para se estabelecer qual tipo de triângulo será formado: isósceles, escaleno ou equilátero.
     * Um triângulo é isósceles quando possui dois lados iguais e um diferente, isto é, A = B ou A = C ou B = C.
     * É escaleno quando possui todos os lados diferentes, ou seja, A <> B e B <> C.
     * O triângulo é considerado equilátero quando possui todos os lados iguais, isto é, quando A = B e B = C.
     * @param args
     */
    public static void main(String[] args) {
        int a, b, c;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe os três lados de um triângulo: ");
        a = entrada.nextInt();
        b = entrada.nextInt();
        c = entrada.nextInt();
        
        if (a < b + c && b < a + c && c < a + b) {
            System.out.println("Os lados informados formam um triângulo.");
            if (a == b || a == c || b == c) {
                System.out.println("O triângulo é do tipo ISOSCELES!!");
            } else if (a != b && b != c) {
                System.out.println("O triângulo é do tipo ESCALENO!!");
            } else if (a == b && b == c) {
                System.out.println("O triângulo é do tipo EQUILATERO!!");
            } 
        } else {
            System.out.println("Os lados informados não formam um triângulo");
        }
    }
    
}

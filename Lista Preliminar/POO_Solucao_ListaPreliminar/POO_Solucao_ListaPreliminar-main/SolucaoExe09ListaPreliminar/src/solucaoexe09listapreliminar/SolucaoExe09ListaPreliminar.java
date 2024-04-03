/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe09listapreliminar;

import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe09ListaPreliminar {

    /**
     * @param args the command line arguments
     * 09.Fazer um programa que: 
     * • Leia e escreva o nome e a altura das moças inscritas em um concurso de 
     *      beleza. Para cada moça, foi informado seu nome e sua altura. 
     *      Para encerrar, será informada a palavra "FIM" no lugar do nome.
     * • Calcule e escreva as duas maiores alturas e quantas moças a possuem.
     * 
     * ATENCAO: A solução deste exercício não faz uso de arrays, o que poderia
     * ser mais apropriado para resolve-lo. Array não foi utilizado por não termos
     * abordado o conteúdo ainda.
     */
    public static void main(String[] args) {
        String nome;
        float altura, maiorAltura1 = 0.0f, maiorAltura2 = 0.0f;
        int qtdMaiorAltura1 = 0, qtdMaiorAltura2 = 0, qtdCandidatas = 0;
        do {
            nome = JOptionPane.showInputDialog("Informe o Nome da candidata: \n"
                    + "*Digite FIM para finalizar o programa.");
            if (nome.equalsIgnoreCase("fim")) break;
            qtdCandidatas++;
            altura = Float.parseFloat(JOptionPane.showInputDialog("Altura: "));
            if (maiorAltura1 == 0.0f) {
                //este if resolve a primeira entrada assumindo a maior altura
                maiorAltura1 = altura;
                qtdMaiorAltura1++;
            } else {
                //neste else é resolvido as demais ocorrências
                if (altura == maiorAltura1) {
                    qtdMaiorAltura1++;
                } else if (altura > maiorAltura1) {
                    maiorAltura2 = maiorAltura1;
                    qtdMaiorAltura2 = qtdMaiorAltura1;
                    maiorAltura1 = altura;
                    qtdMaiorAltura1 = 1;
                } else if (altura == maiorAltura2) {
                    qtdMaiorAltura2++;
                } else if (altura > maiorAltura2) {
                    maiorAltura2 = altura;
                    qtdMaiorAltura2 = 1;
                }
            }
        } while (true);
        
        JOptionPane.showMessageDialog(null,""
                + "Foram informadas " + qtdCandidatas + " candidatas.\n"
                + "A primeira maior altura informada foi de " + maiorAltura1 + 
                "m e tiveram " + qtdMaiorAltura1 + " canditada(s) com esta altura. \n"
                + "A segunda maior altura informada foi de " + maiorAltura2 + 
                "m e tiveram " + qtdMaiorAltura2 + " candidata(s) com esta altura.\n");
    }
    
}

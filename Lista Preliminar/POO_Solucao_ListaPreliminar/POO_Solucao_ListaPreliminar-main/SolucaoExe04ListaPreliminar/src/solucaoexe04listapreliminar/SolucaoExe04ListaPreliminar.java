/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe04listapreliminar;

import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe04ListaPreliminar {

    /**
     * @param args the command line arguments
     * 4.A Secretaria de Meio Ambiente que controla o índice de poluição mantém 
     * 3 grupos de indústrias que são altamente poluentes do meio ambiente. 
     * O índice de poluição aceitável varia de 0 (zero) até 0,25. Se o índice 
     * sobe para 0,3 as indústrias do 1º grupo são intimadas a suspenderem 
     * suas atividades, se o índice crescer para 0,4 as indústrias do 
     * 1º e 2º grupo são intimadas a suspenderem suas atividades, 
     * se o índice atingir 0,5 todos os grupos devem ser notificados a 
     * paralisarem suas atividades. Faça um programa que leia o índice de 
     * poluição medido e emita a notificação adequada aos diferentes grupos 
     * de empresas.
     */
    public static void main(String[] args) {
        //3 grupos de indústrias altamente poluentes
        //indice aceitável: 0 a 0,25
        //índice 0,3 --> indústrias do 1º grupo devem suspender as atividades
        //índice 0,4 --> indústrias do 1º e 2º grupo devem suspender
        //índice 0,5 --> todos os grupos devem paralisar
        float indice = Float.parseFloat(JOptionPane.showInputDialog(""
                + "Informe o índice de poluição: "));
        if (indice <= 0.25) {
            JOptionPane.showMessageDialog(null, "Notificação: o índice de poluição é aceitável");
        } else if (indice <= 0.39) {
            JOptionPane.showMessageDialog(null, "Notificação: As indústrias do 1º grupo devem suspender as suas atividades.");
        } else if (indice <= 0.49) {
            JOptionPane.showMessageDialog(null, "Notificação: As indústrias do 1º e 2º grupo devem suspender as suas atividades.");
        } else {
            JOptionPane.showMessageDialog(null, "Notificação: Todas as indústrias devem suspender as suas atividades imediatamente.");
        }
    }
    
}

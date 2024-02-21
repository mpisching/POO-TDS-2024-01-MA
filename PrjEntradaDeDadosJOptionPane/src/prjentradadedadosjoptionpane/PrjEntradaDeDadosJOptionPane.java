/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prjentradadedadosjoptionpane;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Professor
 */
public class PrjEntradaDeDadosJOptionPane {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        
        JOptionPane.showMessageDialog(null, "Entrada de dados");
//        System.out.print("Nome.........: ");
//        String nome = entrada.next();
        String nome = JOptionPane.showInputDialog("Nomeação: ");
//        System.out.print("Idade........: ");
//        int idade = entrada.nextInt();
        int idade = Integer.parseInt(
                JOptionPane.showInputDialog("Idade: "));
//        System.out.print("Altura.......: ");
//        float altura = entrada.nextFloat();
        float altura = Float.parseFloat(
                JOptionPane.showInputDialog("Altura: "));
//        
//        System.out.println("***** Dados de entrada *****");
//        System.out.println("Nome.......: " + nome);
//        System.out.println("Idade......: " + idade);
//        System.out.println("Altura.....: " + df.format(altura));
//          
        JOptionPane.showMessageDialog(null, 
                "Nomeação: " + nome + "\n" +
                "Idade: " + idade + "\n" +
                "Altura: " + altura);
    }
    
}

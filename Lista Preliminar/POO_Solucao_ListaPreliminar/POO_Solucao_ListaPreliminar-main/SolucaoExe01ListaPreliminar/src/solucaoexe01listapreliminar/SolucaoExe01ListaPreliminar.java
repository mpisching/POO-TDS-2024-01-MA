/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe01listapreliminar;

import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe01ListaPreliminar {

    /**
     * Escreva um aplicativo em Java que lê três inteiros digitados pelo usuário 
     * e exibe a soma, a média, o produto, o maior e o menor desses números em 
     * um diálogo de mensagem de informação. 
     * Nota: o cálculo da média nesse exercício deve resultar em uma 
     * representação da média na forma de inteiro. 
     * Então, se a soma dos valores é 7, a média será 2 e não 2,3333..
     */
    public static void main(String[] args) {
        int x = Integer.parseInt(
                JOptionPane.showInputDialog("Informe o valor de x: "));
        int y = Integer.parseInt(
                JOptionPane.showInputDialog("Informe o valor de y: "));
        int z = Integer.parseInt(
                JOptionPane.showInputDialog("Informe o valor de z: "));
        
        int maior = 0;
        if (x > y && x > z) 
            maior = x;
        else if (y > z)
            maior = y;
        else 
            maior = z;
        
        int menor = (x < y && x < z) ? x : ((y < z ) ? y : z);
        
        JOptionPane.showMessageDialog(null,
            "Para os inteiros x = " + x + ", y = " + y + " e z = " + z + " \n" +
            "a soma é = " + (x + y + z) + "\n" +
            "a média é = " + ((x + y + z) / 3) + "\n" +
            "o produto é = " + (x * y * z) + "\n" +
            "o maior valor é = " + maior + "\n" +
            "o menor valor é = " + menor);
    }
    
}

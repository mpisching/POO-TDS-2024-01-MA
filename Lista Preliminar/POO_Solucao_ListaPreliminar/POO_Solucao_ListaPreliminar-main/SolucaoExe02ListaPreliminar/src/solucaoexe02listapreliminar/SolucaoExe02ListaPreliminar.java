/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe02listapreliminar;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe02ListaPreliminar {

    /**
     * Escreva um aplicativo em Java que lê uma entrada do usuário definindo 
     * o raio de um círculo e que imprime o diâmetro, a circunferência e a área
     * do círculo. Utilize o valor constante 3,14159 para PI. 
     * Nota: você também pode utilizar a constante Math.PI predefinida para o 
     * valor de PI. Essa constante é mais precisa que o valor 3,14159. 
     * A classe Math é definida no pacote java.lang. Utilize as seguintes fórmulas 
     * (onde r é o raio): diâmetro = 2 * r, 
     * circunferência = 2 * PI * r, área = PI * r^2.
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.000");
        float raio = Float.parseFloat(
                JOptionPane.showInputDialog("Informe o raio de um círculo: "));
        //solução simplificada - operações diretas na chamada da função para imprimir os resultados
        JOptionPane.showMessageDialog(null,
            "***** Dados do círculo ******\n" +
            "Diâmetro = " + df.format(2 * raio) + "\n" +
            "Circunferência = " + df.format(2 * Math.PI * raio) + "\n" +
            "Área = " + Math.PI * Math.pow(raio, 2.0));
        
        //ou 
        //solução completa - operações de cálculo separadas da função para imprimir os resultados
//        float area = (float) Math.PI * (float) Math.pow(raio, 2.0);
//        float circunferencia = (float)(2 * Math.PI * raio);
//        float diametro = (2 * raio);
//                JOptionPane.showMessageDialog(null,
//            "***** Dados do círculo ******\n" +
//            "Diâmetro = " + diametro + "\n" +
//            "Circunferência = " + circunferencia + "\n" +
//            "Área = " + area);

        //area = Math.PI * (raio * raio)
    }
    
}

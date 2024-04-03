/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe07listapreliminar;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe07ListaPreliminar {

    /**
     * @param args the command line arguments
     * 07.Desenvolva um aplicativo Java que determine o salário bruto de 
     * cada um de n empregados. A empresa paga “hora normal” pelas 
     * primeiras 40 horas trabalhadas por cada empregado e “horas extras” 
     * com 50% de gratificação para todas as horas trabalhadas além de 40 horas. 
     * Você recebe uma relação dos empregados da empresa, o número de horas 
     * trabalhadas por cada empregado na última semana e o salário-hora de 
     * cada empregado. Seu programa deve ler essas informações para cada 
     * empregado e deve determinar e exibir o salário bruto do empregado. 
     * Utilize diálogo para entrada dos dados. O programa deve finalizar 
     * quando todos os cálculos forem realizados, mostrando o total pago em 
     * salário para todos os empregados.
     */
    public static void main(String[] args) {
        // É necessário conhecer:
        // O número de empregados (n)
        // O número de horas por cada empregado
        // o salario por hora trabalhada de cada empregado
        // --> 50% de gratificação para horas extras
        // O programa deve encontrar o salário bruto de cada empregado
        // fazer um somatório do salários pagos e mostrar
        DecimalFormat df = new DecimalFormat("0.00");
        float totalSalarioBruto = 0.0f;
        int n = Integer.parseInt(JOptionPane.showInputDialog(
                "Informe a quantidade de funcionários:"));
        int i = 1;
        while (i <= n) {
            int qtdHoras = Integer.parseInt(JOptionPane.showInputDialog(
                    "Horas trabalhadas pelo funcionário " + i + ":"));
            float valorHora = Float.parseFloat(JOptionPane.showInputDialog(
                    "Valor da hora: "));
            float salarioBruto = 0.0f;
            if (qtdHoras > 40) {
                salarioBruto = valorHora * 40;
                salarioBruto += (valorHora * 1.50 * (qtdHoras - 40));
            } else {
                salarioBruto = valorHora * qtdHoras;
            }
            totalSalarioBruto += salarioBruto;
            JOptionPane.showMessageDialog(null, "O funcionário " + i + " "
                    + "trabalhou " + qtdHoras + " horas. O salário bruto será "
                            + "de R$ " + df.format(salarioBruto));
            i++;
        }
        
        JOptionPane.showMessageDialog(null, "Valor pago em salários é de R$ " + 
                df.format(totalSalarioBruto));
    }
    
}

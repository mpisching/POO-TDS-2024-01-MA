/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe13listapreliminar;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe13ListaPreliminar {

    /**
     *
     * @author mpisching 13.Um estacionamento cobra uma taxa mínima de R$ 2,00
     * para estacionar por até três horas. Um adicional de R$ 0,50 por hora não
     * necessariamente inteira é cobrado após as três primeiras horas. A taxa
     * máxima para qualquer dado período de 24 horas é R$ 10,00. Pressuponha que
     * nenhum carro fica estacionado por mais de 24 horas por vez. Escreva um
     * programa que calcula e exibem as taxas de estacionamento para cada
     * cliente. O programa deve exibir a cobrança para o cliente atual e
     * calcular e exibir o total dos recibos. O programa deve utilizar o método
     * calcularTaxa para determinar a taxa para cada cliente.
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        int continuar = 0;
        int qtdHoras = 0;
        int numClientesDiario = 0;
        float arrecadacaoTotal = 0.0f, valorAPagar;
        do {
            numClientesDiario++;
            qtdHoras = Integer.parseInt(JOptionPane.showInputDialog(""
                    + "Tempo no estacionamento (em horas): "));
            valorAPagar = calcularTaxa(qtdHoras);
            arrecadacaoTotal += valorAPagar;
            continuar = JOptionPane.showConfirmDialog(null, "Valor a pagar: " + 
                    df.format(valorAPagar) + "\nDeseja continuar?");
        } while (continuar == 0);
        
        JOptionPane.showMessageDialog(null, "Foram atendidos " + numClientesDiario + " clientes no dia.\n"
                + "A arrecadação foi de R$ " + df.format(arrecadacaoTotal));
    }

    private static float calcularTaxa(int qtdHoras) {
            if (qtdHoras <= 0) {
                return 0.0f;
            } else if (qtdHoras <= 3) {
                return 2.0f;
            } else if (qtdHoras < 24) {
                return 2.0f + (0.5f * (qtdHoras - 3));
            } else {
                return 10.0f;
            }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe08listapreliminar;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe08ListaPreliminar {

    /**
     * @param args the command line arguments
     * 08.A Um comerciante deseja fazer o levantamento do lucro das mercadorias
     * que ele comercializa. Para isto, mandou digitar cada mercadoria com o 
     * nome, preço de compra preço de venda das mercadorias. 
     * Fazer um programa que: 
     *   • Determine e escreva quantas mercadorias proporcionam: 
     *        Lucro menor 10 %; 
     *        Lucro entre 10% e 20%; 
     *        Lucro maior que 20%. 
     *   • Determine e escreva o valor total de compra e de venda de todas as 
     *      mercadorias, assim como o lucro total.
     * 
     */
    public static void main(String[] args) {
        int continuar = 0, cont = 0;
        int lucroMenor10, lucroEntre10e20, lucroMaior20;
        float valorTotalCompra = 0.0f, valorTotalVenda = 0.0f;
        DecimalFormat df = new DecimalFormat("0.00");
        lucroMenor10 = lucroEntre10e20 = lucroMaior20 = 0;
        StringBuilder resultado = new StringBuilder();
        resultado.append("Resumo das vendas\n");
        
        do {
            cont++;
            String nome = JOptionPane.showInputDialog("Nome do produto: ");
            float precoCompra = Float.parseFloat(JOptionPane.showInputDialog(""
                    + "Preço de compra do produto " + nome));
            float precoVenda = Float.parseFloat(JOptionPane.showInputDialog(""
                    + "Preço de venda do produto " + nome));
            
            float lucro = precoVenda - precoCompra;
            float margemLucro = 100 * lucro / precoCompra;
            if (margemLucro < 10) {
                lucroMenor10++;
            } else if (margemLucro >= 10 && margemLucro <= 20) {
                lucroEntre10e20++;
            } else {
                lucroMaior20++;
            }
            
            valorTotalCompra += precoCompra;//valorTotalCompra = valorTotalCompra + precoCompra
            valorTotalVenda += precoVenda;

            continuar = JOptionPane.showConfirmDialog(null, "Deseja registrar um novo produto? ", 
                    "Registro de produtos", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
        } while(continuar==0);
        
        float lucroTotal = valorTotalVenda - valorTotalCompra;
        float margemLucroTotal = 100 * lucroTotal / valorTotalCompra;
        
        resultado.append("\nTotal em Vendas = R$ " + valorTotalVenda);
        resultado.append("\nTotal em Compras = R$ " + valorTotalCompra);
        resultado.append("\n========================\n");
        resultado.append("Lucro total = R$ " + lucroTotal);
        resultado.append("\nMargem de Lucro total = ").append(df.format(margemLucroTotal)).append("%");
        resultado.append("\n\nDos " + cont + " produtos informados, \n");
        resultado.append(lucroMenor10).append(" apresenta(m) lucro menor que 10%\n");
        resultado.append(lucroEntre10e20).append(" apresenta(m) lucro entre 10% e 20%\n");
        resultado.append(lucroMaior20).append(" apresenta(m) lucro maior que 20%");
        JOptionPane.showMessageDialog(null, resultado);
        //System.out.println(resultado);
    }
    
}

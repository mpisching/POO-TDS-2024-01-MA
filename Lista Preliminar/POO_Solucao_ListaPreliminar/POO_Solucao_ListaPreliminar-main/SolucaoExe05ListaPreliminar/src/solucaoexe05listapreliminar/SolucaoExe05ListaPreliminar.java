/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe05listapreliminar;

import javax.swing.JOptionPane;

/**
 *
 * @author mpisc
 */
public class SolucaoExe05ListaPreliminar {
    /**
     * 05. Fazer um programa usando a instrução condicional SWITCH..CASE para ler o código de um
     * determinado produto e mostrar a sua classificação. Utilize a seguinte tabela como referência: 
     * Código Classificação 
     * 1 -> Alimento não-perecível
     * 2, 3 ou 4 -> Alimento perecível
     * 5 ou 6 -> Vestuário
     * 7 -> Higiene Pessoal
     * 8 até 15 -> Limpeza e Utensílios domésticos
     * Qualquer outro código Inválido
     */
    public static void main(String[] args) {
        byte codigo = Byte.parseByte(JOptionPane.showInputDialog(""
                + "Informe o código do produto."));
        //1ª forma de implementar a instrução de controle switch case
//        switch (codigo) {
//            case 1: JOptionPane.showMessageDialog(null, "Alimento não perecível."); break;
//            case 2:
//            case 3:
//            case 4: JOptionPane.showMessageDialog(null, "Alimento perecível."); break;
//            case 5: 
//            case 6: JOptionPane.showMessageDialog(null, "Vestuário."); break;
//            case 7: JOptionPane.showMessageDialog(null, "Higiene pessoal."); break;
//            case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15:
//                JOptionPane.showMessageDialog(null, "Limpeza e utensílios domésticos."); break;
//            default: 
//                JOptionPane.showMessageDialog(null, "Código inválido."); break;
//        }
          //2ª forma de implementar a instrução de controle switch case
//        switch (codigo) {
//            case 1: JOptionPane.showMessageDialog(null, "Alimento não perecível."); break;
//            case 2, 3, 4: JOptionPane.showMessageDialog(null, "Alimento perecível."); break;
//            case 5, 6: JOptionPane.showMessageDialog(null, "Vestuário."); break;
//            case 7: JOptionPane.showMessageDialog(null, "Higiene pessoal."); break;
//            case 8, 9, 10, 11, 12, 13, 14, 15:
//                JOptionPane.showMessageDialog(null, "Limpeza e utensílios domésticos."); break;
//            default: 
//                JOptionPane.showMessageDialog(null, "Código inválido."); 
//        }
        //3ª forma de implementar a instrução de controle switch case
        switch (codigo) {
            case 1  -> JOptionPane.showMessageDialog(null, "Alimento não perecível.");
            case 2, 3, 4 -> JOptionPane.showMessageDialog(null, "Alimento perecível.");
            case 5, 6 -> JOptionPane.showMessageDialog(null, "Vestuário.");
            case 7 -> JOptionPane.showMessageDialog(null, "Higiene pessoal.");
            case 8, 9, 10, 11, 12, 13, 14, 15 -> JOptionPane.showMessageDialog(null, "Limpeza e utensílios domésticos.");
            default -> JOptionPane.showMessageDialog(null, "Código inválido."); 
        }

    }
    
}

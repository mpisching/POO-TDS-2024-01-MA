/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe14listapreliminar;

import java.text.DecimalFormat;

/**
 *
 * @author mpisching
 * 14.	Escreva um programa que solicita o total gasto pelo cliente de uma loja, 
 * imprime as opções de pagamento, solicita a opção desejada e imprime o valor
 * total das parcelas (quando houverem). 
 * Opções: 
 *  1)	a vista com 10% de desconto
 *  2)	em duas vezes (preço da etiqueta)
 *  3)	de 3 até 10 vezes com 3% de juros ao mês (somente para compras acima 
 *      de R$ 100,00).
 * OBS: Fazer um método que imprime o menu de opções e solicita a opção 
 * desejada. Este método deverá retornar a opção escolhida e, a partir dela, 
 * o programa principal deve verifica-la (por meio de uma instrução switch)
 * para então ativar o método correspondente (um método para cada opção) 
 * para calcular o valor do produto e parcelas.

 */
public class SolucaoExe14ListaPreliminar {
   private static java.util.Scanner entrada = new java.util.Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        double parcela = 0.0;
        System.out.println("Valor da compra: ");
        double valor = entrada.nextDouble();
        int opcao = menu();
        switch (opcao) {
            case 1: 
                parcela = pagamentoAVista(valor); 
                System.out.println("Pagamento a vista no valor de R$ " + df.format(parcela));
                break;
            case 2:
                parcela = pagamentoEm2x(valor);
                System.out.println("Pagamento em 2x. O valor da parcela é R$ " 
                        + df.format(parcela));
                break;
            case 3:
                int qtdParcelas = 0;
                do {
                    System.out.print("Número de parcelas (entre 3 e 10): ");
                    qtdParcelas = entrada.nextInt();
                    if (qtdParcelas < 3 || qtdParcelas > 10) 
                        System.out.println("Quantidade de parcelas invalida.");
                } while (qtdParcelas < 3 || qtdParcelas > 10);
                parcela = pagamentoParcelado(valor, qtdParcelas);
                System.out.println("O valor da parcela será de R$ " + df.format(parcela));
                break;
            default:
                System.out.println("Opcao inválida.");
        }
    }

    private static int menu() {
        System.out.println("Formas de pagamento: ");
        System.out.print("1 - A vista com 10% de desconto\n"
                + "2 - Em duas vezes (preço da etiqueta)\n"
                + "3 - De 3 a 10 vezes com 3% de juros ao mês\n"
                + "Opção: ");
        int opcao = entrada.nextInt();
        return opcao;
    }

    private static double pagamentoAVista(double valor) {
        return valor - (valor * 0.10);
    }

    private static double pagamentoEm2x(double valor) {
        return valor / 2;
    }

    private static double pagamentoParcelado(double valor, int qtdParcelas) {
        //cálculo de juros compostos
        //for (int i = 0; i < qtdParcelas; i++) {
        //  valor += valor * 0.03;
        //}
        //return valor / qtdParcelas;
        // ou pela fórmula ==> montante = principal * (1+taxa)^prazo
        double montante = valor * Math.pow(1 + 0.03, qtdParcelas);
        return montante / qtdParcelas;
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucaoexe11listapreliminar;

import java.util.Scanner;

/**
 *
 * @author mpisching
 */
public class SolucaoExe11ListaPreliminar {
    /*
    Deseja-se fazer uma pesquisa a respeito do consumo mensal de energia elétrica em uma determinada
cidade. Para isso, são fornecidos os seguintes dados:
 Preço do KWh consumido;
 Número do consumidor;
 Quantidade de KWh consumidos durante o mês;
 Código do tipo de consumidor (residencial, comercial, industrial).
Fazer um programa que:
 Leia os dados descritos acima;
 Calcule:
 Para cada consumidor, o total a pagar;
 O maior consumo verificado;
 O menor consumo verificado;
 O total de consumo para cada um dos três tipos de consumidores;
 A média geral de consumo
    */
    public static Scanner entrada = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maiorConsumo = 0, menorConsumo = 0;
        int totalConCom = 0, totalConRes = 0, totalConInd = 0, totalConsumido = 0;
        int qtdConsumidores = 0;
        float mediaGeralConsumida = 0.0F;
        String opcao = "N";
        
        do {
            //entrada de dados
            System.out.print("Preco kWh.........................: ");
            float precoKWh = Float.parseFloat(entrada.next());
            System.out.print("Numero do consumidor..............: ");
            int numeroConsumidor = Integer.parseInt(entrada.next());
            System.out.print("Quantidade kWh consumido..........: ");
            int qtdKWhConsumido = Integer.parseInt(entrada.next());
            System.out.print("Tipo de consumidor (1, 2, ou 3?)..: ");
            int codTipoConsumidor = Integer.parseInt(entrada.next());

            //processos
            //processo 1 - calcula o total a pagar
            float totalAPagar = precoKWh * qtdKWhConsumido;
            qtdConsumidores++;

            //processo 2 - encontra o maior e menor consumo
            if (qtdConsumidores == 1) {
                maiorConsumo = menorConsumo = qtdKWhConsumido;
            } else {
                if (qtdKWhConsumido > maiorConsumo) {
                    maiorConsumo = qtdKWhConsumido;
                } else if (qtdKWhConsumido < menorConsumo) {
                    menorConsumo = qtdKWhConsumido;
                } 
            }
            //processo 3 - O total de consumo para cada um dos três tipos de consumidores;
            switch (codTipoConsumidor) {
                case 1: totalConRes += qtdKWhConsumido; break;
                case 2: totalConCom += qtdKWhConsumido; break;
                case 3: totalConInd += qtdKWhConsumido; break;
                default: totalConRes += qtdKWhConsumido;//assumindo que qualquer outro tipo de codigo será atribuido ao tipo 1
            }
            
            //processo 4 = calculo da média geral consumida
            totalConsumido += qtdKWhConsumido;
            mediaGeralConsumida = (float)totalConsumido / (float)qtdConsumidores;//operacao de 
            
            //saída
            System.out.println("Total a pagar do consumidor " + numeroConsumidor + " será de R$ " + totalAPagar + ""
                    + " para um consumo total de " + qtdKWhConsumido + " kWh");
            System.out.println("Maior consumo.................: " + maiorConsumo);
            System.out.println("Menor consumo.................: " + menorConsumo);
            System.out.println("Qtd consumido pelo tipo 1 ....: " + totalConRes);
            System.out.println("Qtd consumido pelo tipo 2 ....: " + totalConCom);
            System.out.println("Qtd consumido pelo tipo 3 ....: " + totalConInd);
            System.out.println("Média geral consumida em kWh..: " + mediaGeralConsumida);
            System.out.println("Deseja continuar (S/N)?");
            opcao = entrada.next(); 
        } while (opcao.equalsIgnoreCase("s"));
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solucaoexe10listapreliminar;

/**
 *
 * @author mpisc
 */
public class SolucaoExe10ListaPreliminar {

    /**
     * @param args the command line arguments
     * 10.Uma Universidade deseja fazer um levantamento a respeito de seu 
     * concurso vestibular. Para cada curso é fornecido o seguinte conjunto
     * de valores: 
     * •	Um código do curso; 
     * •	Número de vagas; 
     * •	Número de candidatos do sexo masculino; 
     * •	Número de candidatos do sexo feminino. 
     * Fazer um programa que: 
     * • Calcule e escreva, para cada curso, o número de candidatos por vaga e 
     *      a percentagem de candidatos do sexo feminino (escreva também o 
     *      código correspondente do curso); 
     * • Determine o maior número de candidatos por vaga e escreva esse número 
     *      juntamente com o código do curso correspondente (supor que não haja 
     *      empate); 
     * • Calcule e escreva o total de candidatos.


     */
    public static void main(String[] args) {
        boolean continuar = true;
        java.util.Scanner entrada = new java.util.Scanner(System.in);
        int totalCandidatosGeral = 0, codigoCursoComMaisCandidatos = 0;
        float maiorNumeroCandidatosPorVaga = 0.0f;
        do {
            System.out.print("Código do curso...................: ");
            int codigo = entrada.nextInt();
            System.out.print("Número de vagas...................: ");
            int numeroVagas = entrada.nextInt();
            System.out.print("Número de candidatos masculino....: ");
            int candidatosMasc = entrada.nextInt();
            System.out.print("Número de candidatos feminino.....: ");
            int candidatosFem = entrada.nextInt();
            int totalCandidatos = candidatosFem + candidatosMasc;
            float candidatosPorVaga = totalCandidatos / (float)numeroVagas;
            float percentualCandidatosFeminido = candidatosFem * 100 / (float)totalCandidatos;
            if (candidatosPorVaga > maiorNumeroCandidatosPorVaga) {
                maiorNumeroCandidatosPorVaga = candidatosPorVaga;
                codigoCursoComMaisCandidatos = codigo;
            }
            System.out.println("Curso: [ " + codigo + " ]\n"
                    + "Número de vagas.............: " + numeroVagas + "\n"
                    + "Candidatos por vaga.........: " + candidatosPorVaga + "\n"
                    + "Candidatos feminino (%).....: " + percentualCandidatosFeminido);
            System.out.print("Continuar [S/N]? ");
            char op = entrada.next().charAt(0);
            continuar = op == 's' || op == 'S';
        } while (continuar);
        
        System.out.println("O curso " + codigoCursoComMaisCandidatos + ""
                + " possui o maior número de candidatos por vaga, que é de "
                + maiorNumeroCandidatosPorVaga);
        
    }
    
}

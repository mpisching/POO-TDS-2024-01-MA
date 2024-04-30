/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Relatorio {
//    private Fornecedor fornecedor;
    
    /**
     * O método imprimir implementa o exemplo de uma associação por dependência.
     * Neste tipo de associação não se tem a declaração de um atributo Fornecedor, por exemplo.
     * A relação por dependência estará na passagem de parâmetro do método.
     * @param fornecedor do tipo Fornecedor
     * @return String
     */
    public static String imprimir(Fornecedor fornecedor) {
        StringBuilder sb = new StringBuilder();
        sb.append("Imprimindo os Dados do Fornecedor").append("\n");
        sb.append(fornecedor.getDados()).append("\n");
        return sb.toString();
    }
}

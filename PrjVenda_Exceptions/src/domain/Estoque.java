/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class Estoque {
    private int quantidade;
    private int qtdMaxima;
    private int qtdMinima;
    private ESituacao situacao = ESituacao.INATIVO;//ATIVO / INATIVO / BLOQUEADO 

    public ESituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(ESituacao situacao) {
        this.situacao = situacao;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    /*
        Pelo conceito de encapsulamento o método abaixo, quando disponível, acaba
        tornando as regras de negócios vulneráveis a falhas.
    */
//    public void setQuantidade(int quantidade) {
//        this.quantidade = quantidade;
//    }

    public int getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(int qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }
    
    public void repor(int qtd) throws Exception {
//        if ((qtd + this.quantidade) <= this.qtdMaxima && situacao == ESituacao.ATIVO) {
//            this.quantidade += qtd;
//        } else {
//            System.out.println("a quantidade de reposicao e maior que a capacidade do estoque.");
//            if (situacao != ESituacao.ATIVO) {
//                System.out.println("Situacao nao esta ativa para transacao...");
//            }
//        }
        if ((qtd + this.quantidade) <= this.qtdMaxima && situacao == ESituacao.ATIVO) {
            this.quantidade += qtd;
        } else {
            if (situacao != ESituacao.ATIVO) {
                throw new Exception("Situacao atual do estoque " 
                        + situacao.getDescricao());
            } else {
                throw new Exception("A quantidade de reposicao eh "
                        + "maior que a quantidade maxima permitida.");
            }
        }
    }
    
    public void retirar(int qtd) {
        if (qtd <= this.quantidade && situacao == ESituacao.ATIVO) {
            this.quantidade -= qtd;
        }
    }
}

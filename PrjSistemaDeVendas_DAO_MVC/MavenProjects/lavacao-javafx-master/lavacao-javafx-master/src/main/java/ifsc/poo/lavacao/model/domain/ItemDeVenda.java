package ifsc.poo.lavacao.model.domain;

import java.io.Serializable;

public class ItemDeVenda implements Serializable {

    private int cdItemDeVenda;
    private int quantidade;
    private double valor;
    private Produto produto;
    private Venda venda;

    public ItemDeVenda() {
    }

    public int getCdItemDeVenda() {
        return cdItemDeVenda;
    }

    public void setCdItemDeVenda(int cdItemDeVenda) {
        this.cdItemDeVenda = cdItemDeVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}

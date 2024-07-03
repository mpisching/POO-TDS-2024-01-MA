/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Professor
 */
public class Venda {
    private int id;
    private LocalDate data;
    private BigDecimal valor;
    private boolean pago;
    private double txDesconto;
    private static String empresa;
    
    private Cliente cliente;
    
    private List<ItemDeVenda> itensDeVenda = new ArrayList<>();
    //private List<Produto> produtos = new ArrayList<>();

    public Venda() {
    }

    public Venda(int id, LocalDate data, boolean pago, double txDesconto, Cliente cliente) {
        this.id = id;
        this.data = data;
//        this.valor = valor;//é um valor calculado, tem que passar por um dos métodos totalVenda
        this.pago = pago;
        this.txDesconto = txDesconto;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

//    public void setValor(BigDecimal valor) {
//        this.valor = valor;
//    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public double getTxDesconto() {
        return txDesconto;
    }

    public void setTxDesconto(double txDesconto) {
        this.txDesconto = txDesconto;
    }

    public static String getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(String empresa) {
        Venda.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemDeVenda> getItensDeVenda() {
        return itensDeVenda;
    }

//    public void setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
//        this.itensDeVenda = itensDeVenda;
//    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", data=" + data + ", valor=" + valor + ", pago=" + pago + ", txDesconto=" + txDesconto + ", cliente=" + cliente + ", itensDeVenda=" + itensDeVenda + '}';
    }
    
    public void add(ItemDeVenda itemDeVenda) {
        itensDeVenda.add(itemDeVenda);
        itemDeVenda.setVenda(this);
    }
    
    public void remove(ItemDeVenda itemDeVenda) {
        itensDeVenda.remove(itemDeVenda);
        itemDeVenda.setVenda(null);
    }
    
    public BigDecimal totalVenda() {
        valor = new BigDecimal(0.0);
        for (ItemDeVenda iv : itensDeVenda) {
            valor = valor.add(iv.getValor());           
        }
        return valor;
    }
    
    public BigDecimal totalVenda(double txDesconto) {
        double valorDoDesconto = totalVenda().doubleValue() * 
                txDesconto / 100;
        valor = valor.subtract(new BigDecimal(valorDoDesconto));
        return valor;
    }
    
    public String getCupom() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** CUPOM FISCAL ****").append("\n");
        sb.append("Empresa ").append(empresa).append("\n");
        sb.append("Data: ").append(data.toString())
                .append("\t\tCupom: ").append(id).append("\n");
        sb.append("====================================\n");
        sb.append(">>>> ITEM DE VENDA <<<<").append("\n");
        for (ItemDeVenda iv : itensDeVenda) {
            sb.append(iv.getProduto().getNome())
                    .append("\t\t\t").append(iv.getQuantidade())
                    .append("\t").append(iv.getValor()).append("\n");
        }
        sb.append("====================================\n");
        sb.append("Sub total\t\t\t").append(totalVenda()).append("\n");
        sb.append("Desconto\t\t\t").append(txDesconto).append("\n");
        sb.append("Total\t\t\t\t").append(totalVenda(txDesconto)).append("\n");
        sb.append("====================================\n");
        sb.append("Obrigado pela preferência, volte sempre!!!\n");
        return sb.toString();
    }
}

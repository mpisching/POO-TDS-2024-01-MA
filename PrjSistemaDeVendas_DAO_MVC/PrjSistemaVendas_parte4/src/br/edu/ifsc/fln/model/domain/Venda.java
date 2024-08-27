/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mpisc
 */
public class Venda {
    private int id;
    private LocalDate data;
    private BigDecimal total;
    private boolean pago;
    private double taxaDesconto;
    private static String empresa;
    
    private EStatusVenda statusVenda;
    
    private List<ItemDeVenda> itensDeVenda;
    private Cliente cliente;
    
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

    public BigDecimal getTotal() {
        calcularTotalVenda();
        return total;
    }
    
    
    /* o método setTotal foi retirado para evitar inconsistência, logo, para obter o total é necessário
       executar o método calcularTotalVenda que é chamado pelo método getTotal*/
    //public void setTotal(BigDecimal total) {
    //    this.total =;
    //}

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public double getTaxaDesconto() {
        return taxaDesconto;
    }
    
    public void setTaxaDesconto(double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    public static String getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(String empresa) {
        Venda.empresa = empresa;
    }
    
    public EStatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(EStatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    public List<ItemDeVenda> getItensDeVenda() {
        return itensDeVenda;
    }

    public void setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
        this.itensDeVenda = itensDeVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void add(ItemDeVenda itemVenda) {
        if (itensDeVenda == null) {
            itensDeVenda = new ArrayList<>();
        }
        itensDeVenda.add(itemVenda);
        itemVenda.setVenda(this);
    }
    
    public void remove(ItemDeVenda itemVenda) {
        itensDeVenda.remove(itemVenda);
    }
    
    private void calcularTotalVenda() {
        total = new BigDecimal(0.0);
        for (ItemDeVenda item: this.getItensDeVenda()) {
            total = total.add(item.getValor());
        }
        if (taxaDesconto >= 0) {
            BigDecimal desconto = new BigDecimal(total.doubleValue() * taxaDesconto / 100.0);
            total = total.subtract(desconto);    
        }
    }
       
}

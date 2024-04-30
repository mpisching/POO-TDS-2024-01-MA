/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Categoria;
import domain.Cliente;
import domain.Fornecedor;
import domain.Internacional;
import domain.ItemDeVenda;
import domain.Nacional;
import domain.Produto;
import domain.Relatorio;
import domain.Venda;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        
        Venda.setEmpresa("Alimentos do Brasil");
        
        Cliente cliente = new Cliente(1, "Fulano", "24234", "234234", "rua tal", LocalDate.now());

        Nacional fornecedorNacional = new Nacional("88888888893943", 2, 
                "Positivo", "contato@positivo.com.br", "23423242");

        Fornecedor fornecedor = new Nacional("1234567890", 4, 
                "Needs", "contato@needs.com.br", "373737373737");


        Internacional fornecedorInternacional = 
                new Internacional("2342", "Espanha", 1, "Gluon", 
                        "gluon@gluon.com.es", "999999999");


        Categoria c1 = new Categoria(1, "Alimentos");
        Produto p1 = new Produto(1, "Arroz", "Arroz Parboilizado", new BigDecimal(5.00), c1);
        Produto p2 = new Produto(1, "Leite", "Leite integral", new BigDecimal(7.00), c1);
        Produto p3 = new Produto(1, "Feijão", "Feijão preto", new BigDecimal(8.00), c1);
        
        fornecedor.add(p1);
        fornecedor.add(p2);
        fornecedorNacional.add(p3);
        
        //abrindo a venda -- iniciando uma venda no caixa
        Venda venda = new Venda(1, LocalDate.now(), true, 10, cliente);
        ItemDeVenda iv1 = new ItemDeVenda(1, 1, p1.getPreco(), p1, venda);
        venda.add(iv1);
        ItemDeVenda iv2 = new ItemDeVenda(2, 1, p2.getPreco(), p2, venda);
        venda.add(iv2);
        ItemDeVenda iv3 = new ItemDeVenda(3, 1, p3.getPreco(), p3, venda);
        venda.add(iv3);
        
        print(venda);
    }



    private static void print(Venda venda) {
        System.out.println(venda.getCupom());
    }

}

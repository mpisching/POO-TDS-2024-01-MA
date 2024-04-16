/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domain.Produto;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Professor
 */
public class MainApp {

    public static void main(String[] args) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Converte Objetos
        //System.out.println("Data convertida: "+sdf.parse("02/08/1970"));
        Produto p1 = new Produto();
        p1.setNome("Computador");
        p1.setDataCadastro(new Date());
        
        LocalDate ld = LocalDate.parse("09/04/2024", dtf);
        p1.setDataVencimento(ld);

        Produto p2 = new Produto("Celular", 
                new Date("04/09/2024"), 
                LocalDate.parse("30/01/2024", dtf));

//        Date data = Calendar.getInstance().getTime();
//
//        DateFormat f = DateFormat.getDateInstance();
//        Date data2 = null;
//        try {
//            data2 = f.parse("01/04/2024");
//        } catch (ParseException ex) {
//            System.out.println("Data invalida");
//        }

        Produto p3 = new Produto("Tablet", new Date(), LocalDate.parse("05/01/2022", dtf));

        print(p1);
        print(p2);
        print(p3);
    }

    public static void print(Produto produto) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Data de cadastro: "
                + sdf.format(produto.getDataCadastro()));
        System.out.println("Data de vencimento: " + 
                produto.getDataVencimento().format(dtf));
    }
}

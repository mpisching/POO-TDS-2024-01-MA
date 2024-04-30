/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.Funcionario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Professor
 */
public class MainClass {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        Funcionario.setAuxilioAlimentacao(600.00);
        //Funcionario f1 = new Funcionario(1, "Fulano", 3000.0, 600.0);
//        Funcionario f1 = new Funcionario(1, "Fulano", 3000.0);
//        funcionarios.add(f1);
//        //Funcionario f2 = new Funcionario(2, "Ciclano", 5000.0, 700.00);
//        Funcionario f2 = new Funcionario(2, "Ciclano", 5000.0);
//        funcionarios.add(f2);
//        //Funcionario f3 = new Funcionario(3, "Beltrano", 7000.0, 800.00);
//        Funcionario f3 = new Funcionario(3, "Beltrano", 7000.0);
//        funcionarios.add(f3);
//        
//        print(funcionarios);
        
        //Funcionario f = new Funcionario();
        //System.out.println("Auxilio Alimentacao: " + f.getAuxilioAlimentacao());  
        System.out.println("Auxilio Alimentacao: " + Funcionario.getAuxilioAlimentacao());  
    }

    private static void print(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println("Funcionaro: " + f.toString());
        }
    }
}

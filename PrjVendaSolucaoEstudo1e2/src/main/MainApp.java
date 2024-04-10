/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domain.Cor;
import domain.ECategoria;
import domain.ETipoCombustivel;
import domain.Marca;
import domain.Modelo;
import domain.Motor;
import domain.Veiculo;

/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        Marca m1 = new Marca(1, "Fiat");
        Marca m2 = new Marca(2, "VW");
        Marca m3 = new Marca();
        m3.setId(3);
        m3.setNome("Ford");
        Marca m4 = new Marca();
        
//        print(m1);
//        print(m2);
//        print(m3);
//        print(m4);

        Motor mt1 = new Motor(60, ETipoCombustivel.GASOLINA);
        
        Modelo md1 = new Modelo();
        md1.setId(1);
        md1.setDescricao("Argo");
        md1.setMarca(m1);
        md1.setCategoria(ECategoria.MEDIO);
        //md1.setMotor(mt1);
        
        Modelo md2 = new Modelo(2, "Ranger", m3, ECategoria.GRANDE);
        //md2.setMotor(mt1);
        md2.getMotor().setPotencia(60);
        
        Modelo md3 = new Modelo(3, "Polo", m2, ECategoria.MEDIO);
        md3.getMotor().setPotencia(100);
        md3.getMotor().setTipoCombustivel(ETipoCombustivel.FLEX);
        
        Modelo md4 = new Modelo(4, "UNO", m1, ECategoria.PEQUENO, new Motor(40, ETipoCombustivel.FLEX));
        
//        print(md1);
//        print(md2);
//      
        Cor c1 = new Cor(1, "Preto");
        Cor c2 = new Cor(2, "Azul");
        Cor c3 = new Cor(3, "Branco");
        
        Veiculo v1 = new Veiculo();
        v1.setId(1);
        v1.setPlaca("OKK-1212");
        v1.setObservacoes("Lateral direita raspada");
        v1.setCor(c1);
        v1.setModelo(md2);
        
        Veiculo v2 = new Veiculo(2, "ABC4J12", "NAD", md1, c1);
        
        
        print(v1);
        print(v2);
        printDetalhado(v1);
        printDetalhado(v2);
        
        
    }

    private static void print(Marca m) {
        System.out.println("Marca: " + m);
    }

    private static void print(Modelo md) {
        System.out.println("Modelo: " + md);
    }

    private static void print(Veiculo v) {
        System.out.println("Veiculo: " + v);
    }

    private static void printDetalhado(Veiculo v) {
        System.out.println("\n**** Veiculo ****");
        System.out.println("Placa......: " + v.getPlaca());
        System.out.println("Modelo.....: " + v.getModelo().getDescricao());
        System.out.println("Potencia...: " + v.getModelo().getMotor().getPotencia());
        System.out.println("Marca......: " + 
                v.getModelo().getMarca().getNome());
        System.out.println("Categoria..: " + v.getModelo().getCategoria().getDescription());
        System.out.println("Cor........: " + v.getCor().getNome());
        
    }
}

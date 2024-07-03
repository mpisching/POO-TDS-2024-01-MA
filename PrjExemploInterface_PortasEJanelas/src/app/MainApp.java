/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import domain.JanelaBasculante;
import domain.JanelaCorrer;
import domain.PortaBasculante;
import domain.PortaCorrer;
import domain.pecas.Quarto;

/**
 *
 * @author Professor
 */
public class MainApp {
    
    public static void main(String[] args) {
        Quarto quarto = new Quarto();
        
        quarto.add(new JanelaBasculante());
        quarto.add(new JanelaCorrer());
        quarto.add(new PortaCorrer());
        quarto.add(new PortaBasculante());
        
        quarto.abrirTudo();
        
        quarto.fecharTudo();
    }
    
}

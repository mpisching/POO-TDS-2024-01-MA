/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domain.Porta;
import domain.PortaBasculante;
import domain.PortaDeCorrer;
import domain.PortaGiratoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Professor
 */
public class MainApp {
    public static void main(String[] args) {
        List<Porta> portasCasa = new ArrayList<>();
        Porta porta1 = new PortaGiratoria();
        portasCasa.add(porta1);
        Porta porta2 = new PortaDeCorrer();
        portasCasa.add(porta2);
        Porta porta3 = new PortaBasculante();
        portasCasa.add(porta3);
        
        abrirPortas(portasCasa);
        fecharPortas(portasCasa);
        travarPortas(portasCasa);
      
    }
    
    public static void abrirPortas(List<Porta> portas) {
        for (Porta p: portas) {
            p.abrir();
        }
    }

    private static void fecharPortas(List<Porta> portas) {
        for (Porta p: portas) {
            p.fechar();
        }
    }

    private static void travarPortas(List<Porta> portas) {
        for (Porta p: portas) {
            p.travar();
        }
    }
}

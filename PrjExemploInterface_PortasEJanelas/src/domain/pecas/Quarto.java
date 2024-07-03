/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.pecas;

import domain.IAbertura;
import domain.ITravamento;
import domain.Janela;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Professor
 */
public class Quarto {
    private final List<IAbertura> aberturas = new ArrayList<>();
    
    
    public void add(IAbertura abertura) {
        aberturas.add(abertura);
    }
    
    public void fecharTudo() {
        for (IAbertura abertura : aberturas) {
            abertura.fechar();
        }
    }
    
    public void abrirTudo() {
        for (IAbertura abertura : aberturas) {
            abertura.abrir();
        }
    }
    
}

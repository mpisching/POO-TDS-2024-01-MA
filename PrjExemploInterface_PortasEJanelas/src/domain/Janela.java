/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public abstract class Janela implements IAbertura, ITravamento {
    private boolean fechada = true;
    private boolean travada = true;

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }

    public boolean isTravada() {
        return travada;
    }

    public void setTravada(boolean travada) {
        this.travada = travada;
    }
    
    
    
}

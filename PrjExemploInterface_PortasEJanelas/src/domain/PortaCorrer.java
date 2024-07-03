/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class PortaCorrer extends Porta {
    
@Override
    public void abrir() {
        if (isFechada()) {
            destravar();
            System.out.println("Abrindo " + this.getClass().getSimpleName());
            setFechada(false);
        }
    }

    @Override
    public void fechar() {
        if (!isFechada()) {
            System.out.println("Fechando " + this.getClass().getSimpleName());
            setFechada(true);
            travar();
        }
    }

    @Override
    public void travar() {
        if (!isTravada()) {
            if (isFechada()) {
                System.out.println("Travando " + this.getClass().getSimpleName());
                setTravada(true);
            }
        }
    }

    @Override
    public void destravar() {
        if (isTravada()) {
            System.out.println("Destravando " + this.getClass().getSimpleName());
            setTravada(false);
        }
    }    
}

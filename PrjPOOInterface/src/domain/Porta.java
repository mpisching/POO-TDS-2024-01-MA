/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public abstract class Porta 
        implements IAbertura, ITravamento {
    
    @Override
    public void travar() {
        System.out.println("Travando porta..." );
                //+ this.getClass().getSimpleName());
    }
    
    @Override
    public void destravar() {
        System.out.println("Destravando porta....");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class PortaBasculante extends Porta {
    @Override
    public void abrir() {
        System.out.println("Abrindo porta basculante....");
    }

    @Override
    public void fechar() {
        System.out.println("Fechando porta basculante...");
    }    
}

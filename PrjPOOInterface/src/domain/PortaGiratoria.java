/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Professor
 */
public class PortaGiratoria extends Porta{
    @Override
    public void abrir() {
        System.out.println("Abrindo porta giratória....");
    }

    @Override
    public void fechar() {
        System.out.println("Fechando porta giratória...");
    }    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.edu.ifsc.fln.exception;

/**
 *
 * @author mpisc
 */
public class MovimentacaoEstoqueException extends Exception{

    /**
     * Creates a new instance of <code>MovimentacaoEstoqueException</code>
     * without detail message.
     */
    public MovimentacaoEstoqueException() {
    }

    /**
     * Constructs an instance of <code>MovimentacaoEstoqueException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public MovimentacaoEstoqueException(String msg) {
        super(msg);
    }
}

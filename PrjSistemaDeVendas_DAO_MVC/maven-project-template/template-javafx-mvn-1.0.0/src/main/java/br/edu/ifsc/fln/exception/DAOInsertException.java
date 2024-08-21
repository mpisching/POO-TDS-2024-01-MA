/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package br.edu.ifsc.fln.exception;

/**
 *
 * @author mpisc
 */
public class DAOInsertException extends Exception {

    /**
     * Creates a new instance of <code>DAOInsertException</code> without detail
     * message.
     */
    public DAOInsertException() {
    }

    /**
     * Constructs an instance of <code>DAOInsertException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DAOInsertException(String msg) {
        super(msg);
    }
}

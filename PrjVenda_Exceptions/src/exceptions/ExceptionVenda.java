/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/**
 *
 * @author Professor
 */
public class ExceptionVenda extends Exception {

    /**
     * Creates a new instance of <code>ExceptionVenda</code> without detail
     * message.
     */
    public ExceptionVenda() {
    }

    /**
     * Constructs an instance of <code>ExceptionVenda</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionVenda(String msg) {
        super(msg);
    }
    
    public ExceptionVenda(String msg, Exception cause) {
        super(msg, cause);
    }
}

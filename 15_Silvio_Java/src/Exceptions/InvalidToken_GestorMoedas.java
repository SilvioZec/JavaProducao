/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exceptions;

/**
 *
 * @author efapro01.23
 */
public class InvalidToken_GestorMoedas  extends Exception{

    /**
     * Creates a new instance of <code>InvalidToken_GestorMoedas</code> without
     * detail message.
     */
    public InvalidToken_GestorMoedas() {
    }

    /**
     * Constructs an instance of <code>InvalidToken_GestorMoedas</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidToken_GestorMoedas(String msg) {
        super(msg);
    }
}

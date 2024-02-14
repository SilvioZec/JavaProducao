/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exceptions;

/**
 *
 * @author efapro01.23
 */
public class InvalidDataException_Moeda extends Exception{

    /**
     * Creates a new instance of <code>InvalidDataException_Moeda</code> without
     * detail message.
     */
    public InvalidDataException_Moeda() {
    }

    /**
     * Constructs an instance of <code>InvalidDataException_Moeda</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidDataException_Moeda(String msg) {
        super(msg);
    }
}

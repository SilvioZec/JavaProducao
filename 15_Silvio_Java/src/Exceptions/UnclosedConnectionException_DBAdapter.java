/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exceptions;

/**
 *
 * @author efapro01.23
 */
public class UnclosedConnectionException_DBAdapter extends Exception{

    /**
     * Creates a new instance of
     * <code>UnclosedConnectionException_DBAdapter</code> without detail
     * message.
     */
    public UnclosedConnectionException_DBAdapter() {
    }

    /**
     * Constructs an instance of
     * <code>UnclosedConnectionException_DBAdapter</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UnclosedConnectionException_DBAdapter(String msg) {
        super(msg);
    }
}

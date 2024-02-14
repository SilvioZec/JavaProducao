/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Exceptions;

/**
 *
 * @author efapro01.23
 */
public class QueryFail_DBAdapter extends Exception{

    /**
     * Creates a new instance of <code>QueryFail_DBAdapter</code> without detail
     * message.
     */
    public QueryFail_DBAdapter() {
    }

    /**
     * Constructs an instance of <code>QueryFail_DBAdapter</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public QueryFail_DBAdapter(String msg) {
        super(msg);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg15_silvio_java;

import Business_code.GestorMoedas;
import Business_data.Moeda;
import Exceptions.InvalidToken_GestorMoedas;
import Exceptions.NoConnectionException_DBAdapter;
import Exceptions.QueryFail_DBAdapter;
import Persistence.DBAdapter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silvio
 */
public class testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GestorMoedas gm = null;
        try {
            gm = new GestorMoedas();
            System.out.println("instanciei gm");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            gm.levantaMoeda("1", "1", 6);
            System.out.println("funcionou");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        

    
    }
}

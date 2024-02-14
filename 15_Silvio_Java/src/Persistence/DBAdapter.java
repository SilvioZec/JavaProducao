/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Exceptions.NoConnectionException_DBAdapter;
import Exceptions.QueryFail_DBAdapter;
import Exceptions.UnclosedConnectionException_DBAdapter;
import java.sql.*;

/**
 *
 * @author silvio
 */
public class DBAdapter {
    private String server = "62.28.39.135";
    private String username = "efaSilvio";
    private String password = "123";
    private String dbName = "15silvio_prod_java";
    private String url = "jdbc:mysql://" + server + ":3306/" + dbName;

    private Connection conn = null;
    
    //=====================================================================================CONECTAR E DESCONECTAR BD

    public void conectarBD() throws NoConnectionException_DBAdapter {
        try {
            conn = DriverManager.getConnection(url, username, password);
            //System.out.println("estabeleci ligacao a BD");
        } catch (SQLException ex) {
            throw new NoConnectionException_DBAdapter("Nao foi possivel estabelecer uma ligacao.");
        }
    }
    
    public void desconectarBD() throws UnclosedConnectionException_DBAdapter {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new UnclosedConnectionException_DBAdapter("nao foi possivel fechar a ligacao");
            }
        }
    }
    
    
    //===================================================================================== CONSTRUTOR JA CONECTANDO A DB

    public DBAdapter() throws NoConnectionException_DBAdapter{
        conectarBD();
    }
    
    //===================================================================================== SELECT

    public ResultSet listar (String query) throws QueryFail_DBAdapter {
        try {
            PreparedStatement pt = conn.prepareStatement(query);
            return pt.executeQuery();
        }catch (java.sql.SQLException e){
            throw new QueryFail_DBAdapter("nao foi possivel obter lista.");
        }
    }
    
    //===================================================================================== CRUD
    
    public void executarCRUD (String query) throws QueryFail_DBAdapter{
        try {
            PreparedStatement pt = conn.prepareStatement(query);
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new QueryFail_DBAdapter("nao foi possivel executar o crud");
        }
    }
}

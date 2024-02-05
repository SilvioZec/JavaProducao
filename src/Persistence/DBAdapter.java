package Persistence;

import java.sql.*;

import Business.Contacto;
import Business.Pessoa;
import Business.Veiculo;
import Exception.NoConnectionException;
import Exception.UnclosedConnectionException;
import Exception.QueryFailException;
import com.mysql.cj.protocol.Resultset;

public class DBAdapter {
    private String server = "62.28.39.135";
    private String username = "Raquel";
    private String password = "Silva1234";
    private String dbName = "efa15silvio";
    private String url = "jdbc:mysql://" + server + ":3306/" + dbName;

    private Connection conn = null;

    //=====================================================================================CONECTAR E DESCONECTAR BD
    public void conectarBD() throws NoConnectionException {
        try {
            conn = DriverManager.getConnection(url, username, password);
            //System.out.println("estabeleci ligacao a BD");
        } catch (SQLException ex) {
            throw new NoConnectionException("Nao foi possivel estabelecer uma ligacao.");
        }
    }

    public void desconectarBD() throws UnclosedConnectionException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new UnclosedConnectionException("nao foi possivel fechar a ligacao");
            }
        }
    }

    //===================================================================================== GUARDAR EM BD
    public void gravarPessoaBD(Pessoa p) throws QueryFailException, SQLException {

        PreparedStatement pt = conn.prepareStatement("INSERT INTO pessoas(id, nome, idade) " + "VALUES (?, ?, ?);");
        pt.setString(1, p.getId());
        pt.setString(2, p.getNome());
        pt.setInt(3, p.getIdade());

        try {
            pt.executeUpdate();
        } catch (Exception e) {
            throw new QueryFailException("nao foi possivel adicionar a pessoa");
        }

    }

    public void gravarContactoBD(String idDono, Contacto c) throws QueryFailException, SQLException {

        PreparedStatement pt = conn.prepareStatement("INSERT INTO contactos(tipo, contacto, pessoa_id) " + "VALUES (?, ?, ?);");
        pt.setString(1, c.getTipo());
        pt.setString(2, c.getStringContacto());
        pt.setString(3, idDono);

        try {
            pt.executeUpdate();
        } catch (Exception e) {
            throw new QueryFailException("nao foi possivel adicionar o contacto");
        }

    }

    public void gravarVeiculoBD(String idDono, Veiculo v) throws QueryFailException, SQLException {

        PreparedStatement pt = conn.prepareStatement("INSERT INTO veiculos(matricula, marca, modelo, cilindrada, numero_chassi, lugares, portas, pessoa_id) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        pt.setString(1, v.getMatricula());
        pt.setString(2, v.getMarca());
        pt.setString(3, v.getModelo());
        pt.setInt(4, v.getCilindrada());
        pt.setString(5, v.getNumeroChasi());
        pt.setInt(6, v.getNumeroLugares());
        pt.setInt(7, v.getNumeroPortas());
        pt.setString(8, idDono);

        try {
            pt.executeUpdate();
        } catch (Exception e) {
            throw new QueryFailException("nao foi possivel adicionar o contacto");
        }

    }

    //===================================================================================== BUSCAR EM BD
    public ResultSet buscarPessoas() throws QueryFailException{
        try {
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM pessoas;");
            return pt.executeQuery();
        }catch (java.sql.SQLException e){
            throw new QueryFailException("nao foi possivel obter lista de pessoas.");
        }
    }

    public ResultSet buscarContactos() throws QueryFailException{
        try {
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM contactos;");
            return pt.executeQuery();
        }catch (java.sql.SQLException e){
            throw new QueryFailException("nao foi possivel obter lista de contactos.");
        }
    }

    public ResultSet buscarVeiculos() throws QueryFailException{
        try {
            PreparedStatement pt = conn.prepareStatement("SELECT * FROM veiculos;");
            return pt.executeQuery();
        }catch (java.sql.SQLException e){
            throw new QueryFailException("nao foi possivel obter lista de veiculos.");
        }
    }

    //===================================================================================== DELETE EM BD
}

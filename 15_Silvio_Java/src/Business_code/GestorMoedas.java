/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_code;

import Business_data.Moeda;
import Business_data.MoedaBitcoin;
import Business_data.MoedaEtherum;
import Business_data.MoedaTether;
import Exceptions.InvalidDataException_Moeda;
import Exceptions.InvalidQuantity_GestorMoedas;
import Exceptions.InvalidToken_GestorMoedas;
import Exceptions.NoConnectionException_DBAdapter;
import Exceptions.QueryFail_DBAdapter;
import Persistence.DBAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author silvio
 */
public class GestorMoedas {

    private DBAdapter dbaccess = null;
    private List<Moeda> moedas = new ArrayList<>();

    public GestorMoedas() throws NoConnectionException_DBAdapter, QueryFail_DBAdapter, SQLException, InvalidDataException_Moeda {
        dbaccess = new DBAdapter(); //instancia e ja conecta a db

        carregarLista();
    }

    //============================================================================================================= CARREGA DB PARA ARRAYLIST
    public void carregarLista() throws QueryFail_DBAdapter, SQLException, InvalidDataException_Moeda {
        ResultSet set = dbaccess.listar("select * from moeda");

        while (set.next()) {
            String id = set.getString("idmoeda");
            String nome = set.getString("descricao");
            String token = set.getString("token");
            double cambio = set.getDouble("cambio");
            int quantidade = set.getInt("quantidade");
            double valor_fracionario = set.getDouble("valor_fracionario");

            if (valor_fracionario == 0) {
                if (id.equals("1")) {
                    MoedaBitcoin moeda = new MoedaBitcoin(id, nome, token, cambio, quantidade);
                    moedas.add(moeda);
                }
                else{
                    MoedaEtherum moeda = new MoedaEtherum(id, nome, token, cambio, quantidade);
                    moedas.add(moeda);
                }
                
            } else {
                //adicionar tether
                MoedaTether moeda = new MoedaTether(id, nome, token, cambio, quantidade, valor_fracionario);
                moedas.add(moeda);
            }

        }
    }
    
    //============================================================================================================= VALIDA TOKEN
    public boolean validaToken (String idMoeda, String token){
        boolean validado = false;
        for (Moeda moeda : moedas) {
            if (moeda.getIdentificador().equals(idMoeda) && moeda.getToken().equals(token)) {
                validado = true;
            }
        }
        return validado;
    }
    
    //============================================================================================================= DEPOSITA MOEDA
    
    public void depositaMoeda (String idMoeda, String token, int quantidade) throws InvalidToken_GestorMoedas, QueryFail_DBAdapter, SQLException, InvalidDataException_Moeda{
        if (validaToken(idMoeda, token)) {
            int valor = 0;
            for (Moeda moeda : moedas) {
                if (moeda.getIdentificador().equals(idMoeda)) {
                    valor = moeda.getQuantidade() + quantidade;
                }
            }
            dbaccess.executarCRUD("update moeda set quantidade = " + valor + " where idmoeda = '" + idMoeda + "';");
        }
        else{
            throw new InvalidToken_GestorMoedas("token invalido");
        }
        carregarLista();
    }
    
    //============================================================================================================= LEVANTA MOEDA
    
    public void levantaMoeda (String idMoeda, String token, int quantidade) throws InvalidToken_GestorMoedas, QueryFail_DBAdapter, InvalidQuantity_GestorMoedas, SQLException, InvalidDataException_Moeda{
        if (validaToken(idMoeda, token)) {
            int valor = 0;
            for (Moeda moeda : moedas) {
                if (moeda.getIdentificador().equals(idMoeda)) {
                    valor = moeda.getQuantidade() - quantidade;
                }
            }
            if (valor < 0) {
                throw new InvalidQuantity_GestorMoedas("quantidade nao pode ser negativa");
            }
            dbaccess.executarCRUD("update moeda set quantidade = " + valor + " where idmoeda = '" + idMoeda + "';");
        }
        else{
            throw new InvalidToken_GestorMoedas("token invalido");
        }
        carregarLista();
    }
    
    //============================================================================================================= MUDA CAMBIO
    
    public void alteraCambio (String idMoeda, double cambio) throws InvalidQuantity_GestorMoedas, QueryFail_DBAdapter, SQLException, InvalidDataException_Moeda{
        if (cambio < 0) {
            throw new InvalidQuantity_GestorMoedas("cambio nao pode ser negativo");
        }
        dbaccess.executarCRUD("update moeda set cambio = " + cambio + " where idmoeda = '" + idMoeda + "';");
        carregarLista();
    }
    
    //============================================================================================================= CALCULA VALOR TOTAL CARTEIRA
    
    public double totalCarteira (){
        double total = 0;
        for (Moeda moeda : moedas) {
            if (moeda instanceof MoedaTether) {
                total = total + (moeda.getQuantidade() * MoedaTether.getCambio() * MoedaTether.getFator_estabilizacao());
            }
            else{
                if (moeda instanceof MoedaBitcoin) {
                  total = total + (moeda.getQuantidade() * MoedaBitcoin.getCambio());  
                }
                else{
                    total = total + (moeda.getQuantidade() * MoedaEtherum.getCambio());  
                }
            }
        }
        return total;
    }
    
    //============================================================================================================= VALOR POR TIPO DE MOEDA
    
    public String valorPorMoeda (){
        double bitcoin = 0;
        double etherum = 0;
        double tether = 0;
        for (Moeda moeda : moedas) {
            if (moeda.getIdentificador().equals("1")) {
                bitcoin = moeda.getQuantidade() * MoedaBitcoin.getCambio();
            }
            if (moeda.getIdentificador().equals("2")) {
                etherum = moeda.getQuantidade() * MoedaEtherum.getCambio();
            }
            else{
                tether = moeda.getQuantidade() * MoedaTether.getCambio() * MoedaTether.getFator_estabilizacao();
            }
            
        }
        return "Bitcoin = €" + bitcoin + " // Etherum = €" + etherum + " // Tether = €" + tether;
    }
    
    //============================================================================================================= ALTERA TOKEN MOEDA
    
    public void alteraToken (String idMoeda, String token) throws InvalidToken_GestorMoedas, QueryFail_DBAdapter, SQLException, InvalidDataException_Moeda {
        if (token.isBlank()) {
            throw new InvalidToken_GestorMoedas("token nao pode estar em branco");
        }
        dbaccess.executarCRUD("update moeda set token = '" + token + "' where idmoeda = '" + idMoeda + "';");
        carregarLista();
    }

    //============================================================================================================= TO STRING

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Moeda moeda : moedas) {
            sb.append(moeda.toString() + "\n");
        }
        sb.append("Total por moeda: \n" + valorPorMoeda() + "\n");
        sb.append("Total da carteira : " + totalCarteira());
        return sb.toString();
    }
        
}

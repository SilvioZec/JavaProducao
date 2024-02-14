/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_data;

import Exceptions.InvalidDataException_Moeda;

/**
 *
 * @author silvio
 */
public class MoedaBitcoin extends Moeda {
    private static double cambio;

    //============================================================================================================= CONSTRUCTOR
    
     public MoedaBitcoin(String identificador, String descricao, String token, double cambio, int quantidade) throws InvalidDataException_Moeda {
        setIdentificador(identificador);
        setDescricao(descricao);
        setToken(token);
        setCambio(cambio);
        setQuantidade(quantidade);
        
    }
    
    //============================================================================================================= SETTERS E GETTERS
    public static void setCambio(double cambio) {
        MoedaBitcoin.cambio = cambio;
    }

    public static double getCambio() {
        return cambio;
    }

    
    //============================================================================================================= TOSTRING
    @Override
    public String toString() {
        return "Bitcoin{" + "identificador=" + getIdentificador() + ", descricao=" + getDescricao() + ", token=" + getToken() + ", cambio=" + getCambio() + ", quantidade=" + getQuantidade() + '}';
    }
    
    
    
    
}

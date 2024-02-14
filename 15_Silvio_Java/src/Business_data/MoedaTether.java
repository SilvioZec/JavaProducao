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
public class MoedaTether extends Moeda {
    private static double fator_estabilizacao;
    private static double cambio;

    //============================================================================================================= CONSTRUCTOR

    public MoedaTether(String identificador, String descricao, String token, double cambio, int quantidade, double fator_estabilizacao) throws InvalidDataException_Moeda {
        setIdentificador(identificador);
        setDescricao(descricao);
        setToken(token);
        setCambio(cambio);
        setQuantidade(quantidade);
        setFator_estabilizacao(fator_estabilizacao);
        
    }

    //============================================================================================================= SETTERS E GETTERS
    public static void setCambio(double cambio) {
        MoedaTether.cambio = cambio;
    }

    public static double getCambio() {
        return cambio;
    }

    
    
    public static double getFator_estabilizacao() {
        return fator_estabilizacao;
    }

    public void setFator_estabilizacao(double fator_estabilizacao) {
        this.fator_estabilizacao = fator_estabilizacao;
    }

    //============================================================================================================= TOSTRING
    @Override
    public String toString() {
        return "Tether{" + "identificador=" + getIdentificador() + ", descricao=" + getDescricao() + ", token=" + getToken() + ", cambio=" + getCambio() + ", quantidade=" + getQuantidade() + ", fator_estabilizacao=" + fator_estabilizacao + '}';
    }

    
    
}

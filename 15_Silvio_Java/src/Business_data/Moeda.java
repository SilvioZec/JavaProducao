/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business_data;

import Exceptions.InvalidDataException_Moeda;
import java.util.Objects;

/**
 *
 * @author silvio
 */
public class Moeda {

    private String identificador;
    private String descricao;
    private String token;
    private int quantidade;

    //============================================================================================================= CONTRUCTORS
    public Moeda() {
    }

    public Moeda(String identificador, String descricao, String token, int quantidade) throws InvalidDataException_Moeda {
        setIdentificador(identificador);
        setDescricao(descricao);
        setToken(token);
        setQuantidade(quantidade);
    }

    //============================================================================================================= SETTERS
    public void setIdentificador(String identificador) throws InvalidDataException_Moeda {
        if (!identificador.isEmpty()) {
            this.identificador = identificador;
        } else {
            throw new InvalidDataException_Moeda("id nao pode ser vazio");
        }
    }

    public void setDescricao(String descricao) throws InvalidDataException_Moeda {
        if (!descricao.isEmpty()) {
            this.descricao = descricao;
        } else {
            throw new InvalidDataException_Moeda("descricao nao pode ser vazia");
        }
    }

    public void setToken(String token) throws InvalidDataException_Moeda {
        if (!token.isEmpty()) {
            this.token = token;
        } else {
            throw new InvalidDataException_Moeda("token nao pode ser vazio");
        }
    }

    public void setQuantidade(int quantidade) throws InvalidDataException_Moeda {
        this.quantidade = quantidade;
    }

    //============================================================================================================= GETTERS
    public String getIdentificador() {
        return identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getToken() {
        return token;
    }

    public int getQuantidade() {
        return quantidade;
    }

    //============================================================================================================= TOSTRING E EQUALS

    @Override
    public String toString() {
        return "Moeda{" + "identificador=" + identificador + ", descricao=" + descricao + ", token=" + token + ", quantidade=" + quantidade + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.identificador);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + Objects.hashCode(this.token);
        hash = 89 * hash + this.quantidade;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Moeda other = (Moeda) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return Objects.equals(this.token, other.token);
    }
    

    
}

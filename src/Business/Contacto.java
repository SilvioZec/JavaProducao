package Business;

import Exception.InvalidException;

public class Contacto {
    private String tipo;
    private String contacto;

    public Contacto(String tipo, String contacto) throws InvalidException {
        this.tipo = tipo;
        setContacto(contacto, tipo);
    }

    public void setContacto(String contacto, String tipo) throws InvalidException {
        switch (tipo) {
            case "e-mail":
                if (contacto.matches("^([\\w-]+(?:\\.[\\w-]+)*)@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}$")) {
                    this.contacto = contacto;
                } else {
                    throw new InvalidException("e-mail invalido");
                }
                break;
            case "tel_pessoal":
                if (contacto.matches("^\\+?[1-9]\\d{1,14}$")) {
                    this.contacto = contacto;
                } else {
                    throw new InvalidException("numero invalido");
                }
                break;
            case "tel_empresa":
                if (contacto.matches("^\\+?[1-9]\\d{1,14}$")) {
                    this.contacto = contacto;
                } else {
                    throw new InvalidException("numero invalido");
                }
                break;
            default:
                throw new InvalidException("tipo de contacto nao existe");
        }
    }

    public String getContacto() {
        return "Contacto{" +
                "tipo='" + tipo + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    public String getStringContacto (){
        return contacto;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "{" + "tipo=" + tipo + ", contacto=" + contacto + '}';
    }


}

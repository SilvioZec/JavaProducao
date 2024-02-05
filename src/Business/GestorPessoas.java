package Business;

import java.util.ArrayList;
import java.util.Hashtable;
import Exception.NotFoundException;
import Exception.InvalidException;

public class GestorPessoas {
    //Cria lista de pessoas
    private Hashtable <String, Pessoa> pessoas = new Hashtable<>();
    //cria lista de contactos e associa a pessoas
    private Hashtable<String, ArrayList<Contacto>> contactos = new Hashtable<String, ArrayList<Contacto>>();

    //================================================================================================= CONTAINS
    public boolean containsContacto (String idDono){
        return contactos.containsKey(idDono);
    }
    public boolean contains(Pessoa pessoa){
        if (pessoas.contains(pessoa)){
            return true;
        }
        return false;
    }
    public boolean contains(String idPessoa){
        return pessoas.containsKey(idPessoa);
    }

    //================================================================================================= ADICIONAR PESSOA
    /**
     * recebe objeto pessoa e arrayLists de objetos contacto e os adiciona a Hashtable
     * @param pessoa
     * @throws NullPointerException caso id seja nulo
     */
    public void addPessoa(Pessoa pessoa) throws NullPointerException{
        try {
            pessoas.put(pessoa.getId(), pessoa);
        }catch (NullPointerException e){
            throw new NullPointerException("id nao pode ser nulo");
        }

    }
    //================================================================================================= REMOVE PESSOA

    /**
     * remove pessoa de acordo com id
     * @param id
     * @throws NotFoundException caso nao encontre o id na Hashtable
     */
    public void removePessoa (String id) throws NotFoundException {
        try {
            pessoas.remove(id);
            contactos.remove(id);
        } catch (NullPointerException ex){
            throw new NotFoundException("id nao encontrado");
        }

    }
    //================================================================================================= ADD CONTACTO

    public void addContacto (String idpessoa, Contacto contacto) throws NotFoundException{
        if (!pessoas.containsKey(idpessoa)){
            throw new NotFoundException("dono do contacto nao encontrado. adicione-o primeiro");
        }
        ArrayList<Contacto> arrayContactos = null;
        if (contactos.containsKey(idpessoa)){
            arrayContactos = contactos.get(idpessoa);
            arrayContactos.add(contacto);
        }
        else {
            arrayContactos = new ArrayList<>();
            arrayContactos.add(contacto);
        }
        contactos.put(idpessoa, arrayContactos);
    }

    //================================================================================================= REMOVER CONTACTO
    public void removeContacto (String idDono, Contacto contacto) throws NotFoundException, InvalidException{
        if (!contactos.containsKey(idDono)){
            throw new NotFoundException("dono do contacto nao encontrado.");
        }
        ArrayList<Contacto> arrayContactos = contactos.get(idDono);
        if (arrayContactos.size() == 1){
            throw new InvalidException("ao menos um contacto e necessario. Nao e possivel remover o unico contacto restante");
        }
        if (!arrayContactos.remove(contacto)){
            throw new NotFoundException("Contacto nao existe sob este id");
        }
    }

    //================================================================================================= TOSTRING


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Pessoa pessoa : pessoas.values()) {
            sb.append("Pessoa: ").append(pessoa.toString()).append("\n");

            if (contactos.containsKey(pessoa.getId())) {
                ArrayList<Contacto> contatos = contactos.get(pessoa.getId());

                sb.append("Contatos:\n");

                for (Contacto contato : contatos) {
                    sb.append(contato.toString()).append("\n");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

package API_presentation_business;

import Business.*;
import Exception.NotFoundException;
import Exception.OversizeException;
import Exception.InvalidException;

import java.util.ArrayList;

public class ProgramController {
    private GestorPessoas gestorPessoas = new GestorPessoas();
    private GestorVeiculos gestorVeiculos = new GestorVeiculos();

    //================================================================================================= ADICIONAR

    /**
     * adiciona pessoa a Hashtable de pessoas
     *
     * @param pessoa
     * @throws NullPointerException caso pessoa tenha id nulo
     */
    public void add(Pessoa pessoa) throws NullPointerException {
        gestorPessoas.addPessoa(pessoa);
    }

    /**
     * adiciona contacto a pessoa
     *
     * @param idDono
     * @param contacto
     * @throws NotFoundException caso nao encontre id do dono
     */
    public void add(String idDono, Contacto contacto) throws NotFoundException {
        gestorPessoas.addContacto(idDono, contacto);
    }

    /**
     * adiciona veiculo a hashtable de veiculos e o liga a pessoa
     *
     * @param idDono
     * @param veiculo
     * @throws OversizeException caso ja existam 3 veiculos ligados a pessoa
     */
    public void add(String idDono, Veiculo veiculo) throws OversizeException {
        gestorVeiculos.addVeiculo(idDono, veiculo);
    }

    //================================================================================================= REMOVER

    /**
     * remove pessoa, seus veiculos e contactos das hashtables
     *
     * @param idPessoa
     * @throws NotFoundException caso nao encontre id da pessoa na lista
     */
    public void remove(String idPessoa) throws NotFoundException {
        if (!gestorPessoas.contains(idPessoa)) {
            throw new NotFoundException("pessoa nao encontrada");
        }
        //caso existam veiculos sob tal pessoa, os remove tambem
        if (gestorVeiculos.contains(idPessoa)) {
            ArrayList<String> listaMatriculas = gestorVeiculos.listarMatriculas(idPessoa);
            for (String matricula : listaMatriculas) {
                gestorVeiculos.removeVeiculo(idPessoa, matricula);
            }
        }
        //independentemente, remove pessoa
        gestorPessoas.removePessoa(idPessoa);

    }

    /**
     * remove um unico contacto da pessoa.
     *
     * @param idDono
     * @param contacto
     * @throws NotFoundException caso contacto nao seja encontrado
     * @throws InvalidException  caso haja apenas um contacto
     */
    public void remove(String idDono, Contacto contacto) throws NotFoundException, InvalidException {
        gestorPessoas.removeContacto(idDono, contacto);
    }

    /**
     * remove um unico veiculo da pessoa, de acordo com sua matricula.
     *
     * @param idDono
     * @param matricula
     * @throws NotFoundException caso nao encontre a matricula.
     */
    public void remove(String idDono, String matricula) throws NotFoundException {
        gestorVeiculos.removeVeiculo(idDono, matricula);
    }
    //================================================================================================= LISTAR

    public String listarPessoas() {
        return gestorPessoas.toString();
    }

    public String listarVeiculos() {
        return gestorVeiculos.toString();
    }

    public String listarVeiculos(String idDono) throws NotFoundException {
        return gestorVeiculos.listarVeiculosPessoa(idDono);
    }
    //================================================================================================= CONTAINS

    public boolean existePessoa(String idPessoa) {
        return gestorPessoas.contains(idPessoa);
    }
}

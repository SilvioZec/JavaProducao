package Business;

import java.util.ArrayList;
import java.util.Hashtable;
import Exception.NotFoundException;
import Exception.OversizeException;

public class GestorVeiculos {
    private Hashtable<String, ArrayList<Veiculo>> veiculos = new Hashtable<>();

    //================================================================================================= CONTAINS PESSOA
    public boolean contains(String idPessoa){
        return veiculos.containsKey(idPessoa);
    }
    //================================================================================================= ADICIONAR VEICULO
    /**
     * adiciona veiculo a uma hashtable identificando-o
     *
     * @param idDono
     * @param veiculo
     */
    public void addVeiculo(String idDono, Veiculo veiculo) throws OversizeException{
        ArrayList<Veiculo> arrayVeiculos;

        if (veiculos.containsKey(idDono)){
            arrayVeiculos = veiculos.get(idDono);
        }
        else {
            arrayVeiculos = new ArrayList<>();
            veiculos.put(idDono, arrayVeiculos);
        }
        if (arrayVeiculos.size() > 3){
            throw new OversizeException("Ja existem 3 veiculos registados para esta pessoa");
        }
        arrayVeiculos.add(veiculo);
    }
    //================================================================================================= REMOVE VEICULO
    /**
     * encontra veiculo com base no id do dono e o remove da lista com base em sua matricula
     * @param IdDono
     * @param matricula
     * @throws NotFoundException caso nao enontre o dono do veiculo ou a matricula
     */
    public void removeVeiculo(String IdDono, String matricula) throws NotFoundException {

        if (veiculos.containsKey(IdDono)){
            ArrayList<Veiculo> arrayVeiculos = veiculos.get(IdDono);
            Veiculo veiculoRemover = null;
            boolean removido = false;
            for (Veiculo veiculo : arrayVeiculos) {
                if (veiculo.getMatricula().equals(matricula)){
                    veiculoRemover = veiculo;
                    removido = true;
                }
            }
            if (removido){
                arrayVeiculos.remove(veiculoRemover);
            }
            else {
                throw new NotFoundException("matricula nao encontrada");
            }
        }
        else {
            throw new NotFoundException("id nao encontrado");
        }
    }

    //================================================================================================= MATRICULAS DE VEICULOS DA PESSOA
    public ArrayList <String> listarMatriculas (String idDono){
        ArrayList <String> listaMatriculas = new ArrayList<>();
        ArrayList<Veiculo> arrayVeiculos = veiculos.get(idDono);
        for (Veiculo veiculo : arrayVeiculos) {
            listaMatriculas.add(veiculo.getMatricula());
        }
        return listaMatriculas;
    }

    //================================================================================================= TOSTRING

    public String listarVeiculosPessoa (String idDono) throws NotFoundException{
        if (!veiculos.containsKey(idDono)){
            throw new NotFoundException("pessoa nao possui veiculos");
        }
        StringBuilder sb = new StringBuilder();
        ArrayList <Veiculo> arrayVeiculos = veiculos.get(idDono);
        for (Veiculo veiculo : arrayVeiculos) {
            sb.append(veiculo.toString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Veiculo> value : veiculos.values()) {
            for (Veiculo veiculo : value) {
                sb.append(veiculo.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}

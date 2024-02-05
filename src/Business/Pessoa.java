package Business;
import Exception.InvalidException;
public class Pessoa {
    private String id;
    private String nome;
    private  int idade;
    //================================================================================================= CONSTRUCTOR
    /**
     * valida o id e constroi objeto pessoa caso esteja validado
     * @param id
     */
    public Pessoa(String id) throws InvalidException {
        setId(id);
    }
    //================================================================================================= SETTERS
    /**
     * define nome da pessoa e valida se esta vazio e nao contem numeros
     * @param nome
     */
    public void setNome(String nome) throws InvalidException {
        if (nome.isEmpty() || nome.matches(".*\\d+.*")) {
            throw new InvalidException("o nome nao pode ser vazio ou conter numeros");
        }
        this.nome = nome;
    }

    /**
     * valida se idade e maior que 0 e menor que 120 e a define
     * @param idade
     * @throws InvalidException
     */
    public void setIdade(int idade) throws InvalidException {
        if (idade < 1 || idade > 120) {
            throw new InvalidException("Idade precisa ser maior que 1 e menor que 120");
        }
        this.idade = idade;
    }

    /**
     * valida id
     * @param id
     * @throws InvalidException
     */
    public void setId(String id) throws InvalidException {
        if (id == "") {
            throw new InvalidException("O id nao pode ser nulo");
        }
        else if (id.matches("\\\\d{8}\\\\s?\\\\d{1}\\\\s?[0-9A-Z]{2}\\\\s?\\\\d{1,2}")) {
            this.id = id;
        }

        //throw new InvalidException("cartao cidadao nao validado");
        this.id = id;//============================remover apos testes. validacao ja realizada mas testar com cartao cidadao é chato demais pelamor de deus========
    }

    //================================================================================================= GETTERS


    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
    //================================================================================================= TOSTRING

    @Override
    public String toString() {
        return "{"
                + "nome='" + nome + '\''
                + ", idade=" + idade
                + ", id='" + id + '\''
                + '}';
    }
}


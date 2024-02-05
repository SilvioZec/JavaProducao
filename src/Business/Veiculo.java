package Business;
import Exception.InvalidException;
public class Veiculo {
    private String matricula;

    private String marca;

    private String modelo;

    private Integer cilindrada;

    private String numeroChasi;

    private Integer numeroLugares;

    private Integer numeroPortas;

    //================================================================================================= SETTERS

    /**
     * valida de matricula nao e nula
     * @param matricula
     * @throws InvalidException
     */
    public void setMatricula(String matricula) throws InvalidException {
        if (matricula.isEmpty()){
            throw new InvalidException("Matricula nao pode ser nula");
        }
        this.matricula = matricula;
    }

    public void setMarca(String marca) throws InvalidException {
        this.marca = marca;
    }

    public void setModelo(String modelo) throws InvalidException {
        this.modelo = modelo;
    }

    public void setCilindrada(Integer cilindrada) throws InvalidException {
        this.cilindrada = cilindrada;
    }

    public void setNumeroChasi(String numeroChasi) throws InvalidException {
        this.numeroChasi = numeroChasi;
    }

    public void setNumeroLugares(Integer numeroLugares)throws InvalidException {
        if (numeroLugares > 0) {
            this.numeroLugares = numeroLugares;
        }
        else{
            throw new InvalidException("O veiculo precisa ter ao menos um lugar. isto nao e um drone");
        }
    }

    public void setNumeroPortas(Integer numeroPortas) throws InvalidException {
        if (numeroPortas > 0) {
            this.numeroPortas = numeroPortas;
        }
        else{
            throw new InvalidException("O veiculo precisa ter ao menos uma porta, ou entra pelo tecto?");
        }
    }
    //================================================================================================= GETTERS

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getCilindrada() {
        return cilindrada;
    }

    public String getNumeroChasi() {
        return numeroChasi;
    }

    public Integer getNumeroLugares() {
        return numeroLugares;
    }

    public Integer getNumeroPortas() {
        return numeroPortas;
    }

    //================================================================================================= CONSTRUCTOR

    /**
     * valida matricula e caso positivo cria objeto veiculo
     * @param matricula
     * @throws InvalidException
     */
    public Veiculo(String matricula) throws InvalidException {
        setMatricula(matricula);
    }


    //================================================================================================= TOSTRING

    @Override
    public String toString() {
        return "Veiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cilindrada=" + cilindrada +
                ", numeroChasi='" + numeroChasi + '\'' +
                ", numeroLugares=" + numeroLugares +
                ", numeroPortas=" + numeroPortas +
                '}';
    }
}

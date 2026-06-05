import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private int id;
    private String nome;
    private TipoFuncionario tipo;
    private List<RegistroPonto> registros;

    public Funcionario(int id, String nome, TipoFuncionario tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.registros = new ArrayList<>();
    }
    
    public void adicionarRegistro(RegistroPonto registro) {
        this.registros.add(registro);
    }

    @Override
    public String toString() {
        return String.format("\n ID: %d\n Nome: %s \n Tipo: %s\n", id, nome, tipo);
    }
    
    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public TipoFuncionario getTipo() { return tipo; }
    public List<RegistroPonto> getRegistros() { return registros; }
}
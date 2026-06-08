import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

enum TipoContrato {
    CLT, PJ, ESTAGIO
}

class Funcionario {
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private TipoContrato tipoContrato;

    // Construtor e Getters
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, TipoContrato tipoContrato) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
    }

    public String getNome() { return nome; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public BigDecimal getSalario() { return salario; }
    public TipoContrato getTipoContrato() { return tipoContrato; }
    
    // Método auxiliar para calcular a idade
    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
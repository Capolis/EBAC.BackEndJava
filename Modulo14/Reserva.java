public class Reserva {
    // if ternário usado no método setNumeroDias para garantir que o valor mínimo seja 1
    
    // Atributos privados (Encapsulamento)
    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    // Construtor completo
    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        // Utiliza os métodos modificadores para garantir a validação
        setNumeroDias(numeroDias);
        setValorDiaria(valorDiaria);
    }

    // Sobrecarga de construtor (Apenas nome e tipo de quarto)
    public Reserva(String nomeHospede, String tipoQuarto) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        this.numeroDias = 1;      // Valor mínimo
        this.valorDiaria = 100.0;  // Valor padrão
    }

    // Método para calcular o valor total da hospedagem
    public double calcularValorTotal() {
        return this.numeroDias * this.valorDiaria;
    }

    // Método toString para exibição formatada dos dados
    @Override
    public String toString() {
        return "Hóspede: " + this.nomeHospede + 
               " | Quarto: " + this.tipoQuarto + 
               " | Dias: " + this.numeroDias + 
               " | Diária: R$ " + String.format("%.2f", this.valorDiaria) + 
               " | Total: R$ " + String.format("%.2f", calcularValorTotal());
    }

    // GETTERS E SETTERS
    
    public String getNomeHospede() {
        return this.nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getTipoQuarto() {
        return this.tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getNumeroDias() {
        return this.numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        // Validação: Número de dias não pode ser menor que 1
        numeroDias = (numeroDias < 1) ? 1 : numeroDias;
        this.numeroDias = numeroDias;
    }

    public double getValorDiaria() {
        return this.valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        // Validação: Valor da diária não pode ser negativo ou zero
        if (valorDiaria <= 0) {
            this.valorDiaria = 1.0;
        } else {
            this.valorDiaria = valorDiaria;
        }
    }
}
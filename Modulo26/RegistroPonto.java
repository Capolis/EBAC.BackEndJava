import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroPonto {
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime saida;

    public RegistroPonto(LocalDate data, LocalTime entrada, LocalTime saida) {
        this.data = data;
        this.entrada = entrada;
        this.saida = saida;
    }

    
    public long getMinutosTrabalhados() {
        long minutosTotais = Duration.between(entrada, saida).toMinutes();
        // Desconta 1 hora (60 minutos) de almoço por padrão
        return minutosTotais - 60;
    }
    
    @Override
    public String toString() {
        long minutos = getMinutosTrabalhados();
        long horas = minutos / 60;
        long minRestantes = minutos % 60;
        
        return String.format("Data: %s | Entrada: %s | Saída: %s | Tempo Trabalhado (desscontado 1 hora do almoço): %02dh%02d", 
        data, entrada, saida, horas, minRestantes);
    }

    // Getters
    public LocalDate getData() { return data; }
    public LocalTime getEntrada() { return entrada; }
    public LocalTime getSaida() { return saida; }
}
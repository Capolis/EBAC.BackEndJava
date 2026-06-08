import java.util.Arrays;
import java.util.List;

public class PraticaLambda {

    public static void main(String[] args) {
        
        List<String> nomes = Arrays.asList(
            "Ana", "Carlos", "Amanda", "Bruno", 
            "Aline", "Daniel", "Artur", "Beatriz", 
            "André", "Fernanda"
        );

        // Interface usando expressão Lambda
        FiltroNome filtro = nome -> nome.startsWith("A");

        System.out.println("Nomes que começam com a letra 'A':");

        //Utilizando stream().filter() com a nossa interface para aplicar o filtro
        nomes.stream()
             .filter(nome -> filtro.testar(nome)) 
             .forEach(nome -> System.out.println(nome)); // Exibindo no console
    }
}
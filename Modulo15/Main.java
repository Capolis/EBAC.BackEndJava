import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrada (número de degraus): ");
        int degraus = scanner.nextInt();

        // Instancia a escada e calcula
        Escada escada = new Escada(degraus);
        int resultado = escada.calcularPossibilidades();

        System.out.println("Saída correta: " + resultado);

        scanner.close();
    }
}
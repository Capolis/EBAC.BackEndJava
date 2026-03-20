import java.util.Scanner;

public class ComparadorNumeros {
    public static void main(String[] args) {
        // 1. Instanciar o Scanner para ler a entrada do teclado
        Scanner scanner = new Scanner(System.in);

        // 2. Solicitar os dois números ao usuário
        System.out.print("Digite o primeiro número inteiro: ");
        int primeiroNumero = scanner.nextInt();

        System.out.print("Digite o segundo número inteiro: ");
        int segundoNumero = scanner.nextInt();

        // 3. Verificar as condições solicitadas
        if (primeiroNumero > segundoNumero) {
            System.out.println("O maior número é: " + primeiroNumero + ".");
        } 
        else if (segundoNumero > primeiroNumero) {
            System.out.println("O maior número é: " + segundoNumero + ".");
        } 
        else {
            System.out.println("Os números são iguais!");
        }

        // 4. Fechar o scanner
        scanner.close();
    }
}
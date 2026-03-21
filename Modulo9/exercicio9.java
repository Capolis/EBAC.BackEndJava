import java.util.Scanner;

public class exercicio9 {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        // Chamada da primeira parte: Tabuada com While
        executarTabuada(leitor);
        
        // Chamada da segunda parte: Menu com Do-While
        executarMenuInterativo(leitor);

        leitor.close();
    }

    // --- MÉTODO: TABUADA ---
    public static void executarTabuada(Scanner scanner) {
        System.out.println("\n--- GERADOR DE TABUADA ---");
        System.out.print("Digite um número para ver a tabuada: ");
        int numero = scanner.nextInt();
        
        int contador = 1;
        while (contador <= 10) {
            System.out.println(numero + " x " + contador + " = " + (numero * contador));
            contador++;
        }
        System.out.println("--------------------------");
    }

    // --- MÉTODO: MENU ---
    public static void executarMenuInterativo(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n--- MENU DE OPÇÕES ---");
            System.out.println("1: Continuar");
            System.out.println("2: Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza de buffer

            if (opcao == 1) {
                System.out.print("Digite uma palavra ou frase: ");
                String frase = scanner.nextLine();
                System.out.println("Você escreveu: \"" + frase + "\"");
            } else if (opcao == 2) {
                System.out.println("Obrigado por utilizar o nosso sistema, espero que tenha gostado.");
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 2); // O loop continua até a escolha da opção de sair (2)
    }
}
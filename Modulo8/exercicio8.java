import java.util.Scanner;

public class exercicio8 {

    public static void main(String[] args) {
        // O main agora é apenas o "maestro" da orquestra
        Scanner leitor = new Scanner(System.in);

        String nome = solicitarNome(leitor);
        int n = solicitarNumero(leitor);

        exibirCrescente(n);
        exibirDecrescente(n);
        processarNome(nome, n);

        leitor.close();
        System.out.println("\nPrograma finalizado com sucesso!");
    }

    // --- FUNÇÕES (MÉTODOS) ---

    public static String solicitarNome(Scanner scanner) {
        System.out.print("Digite o seu nome: ");
        return scanner.nextLine();
    }

    public static int solicitarNumero(Scanner scanner) {
        int num = -1;
        while (num < 0) {
            System.out.print("Digite um número inteiro positivo (n): ");
            num = scanner.nextInt();
            if (num < 0) {
                System.out.println("Erro: O número deve ser positivo!");
            }
        }
        return num;
    }

    public static void exibirCrescente(int n) {
        System.out.println("\n--- Contagem Crescente ---");
        for (int i = 0; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void exibirDecrescente(int n) {
        System.out.println("\n--- Contagem Decrescente ---");
        for (int i = n; i >= 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void processarNome(String nome, int n) {
        System.out.println("\n--- Verificação de Nome ---");
        if (nome.length() > 6) {
            System.out.println("Nome longo (" + nome.length() + " letras). Repetindo " + n + " vezes:");
            for (int i = 1; i <= n; i++) {
                System.out.println(i + ". " + nome);
            }
        } else {
            System.out.println("Nome curto. Exibindo 1 vez:");
            System.out.println(nome);
        }
    }
}
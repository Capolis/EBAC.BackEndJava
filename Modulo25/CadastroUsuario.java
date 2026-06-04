import java.util.Scanner;

public class CadastroUsuario {

    public static void cadastrarUsuario(String nome, int idade) throws IdadeInvalidaException {
        if (idade < 18) {
            throw new IdadeInvalidaException("O usuário precisa ser maior de idade para se cadastrar.");
        }
        System.out.println("Usuário " + nome + ", foi cadastrado com sucesso.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do usuário: ");
        int idade = scanner.nextInt();

        // Bloco de tratamento de exceções
        try {
            cadastrarUsuario(nome, idade);
            
        } catch (IdadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            
        } finally {
            System.out.println("Processo de cadastro finalizado.");
            scanner.close();
        }
    }
}
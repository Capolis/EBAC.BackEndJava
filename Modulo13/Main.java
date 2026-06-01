import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define a quantidade de alunos e provas
        System.out.print("Quantos alunos deseja cadastrar? ");
        int numeroDeEstudantes = scanner.nextInt();

        System.out.print("Quantas provas foram realizadas no ano? ");
        int numeroDeExames = scanner.nextInt();

        // Vetor para armazenar os objetos Estudante (Alunos)
        Estudante[] estudantes = new Estudante[numeroDeEstudantes];

        // Loop para cadastrar cada aluno
        for (int i = 0; i < numeroDeEstudantes; i++) {
            scanner.nextLine(); // Limpa o buffer do Scanner
            
            System.out.println("\n--- Cadastro do Aluno " + (i + 1) + " ---");
            System.out.print("Nome do aluno: ");
            String nome = scanner.nextLine();

            System.out.print("ID (Matrícula): ");
            int id = scanner.nextInt();

            // Instancia o objeto
            Estudante estudante = new Estudante(nome, id, numeroDeExames);
            double[] notaAtual = new double[numeroDeExames];

            // Loop para cadastrar e validar as notas
            for (int j = 0; j < numeroDeExames; j++) {
                double grade = -1; // Inicializa com valor inválido para forçar a entrada no loop
                
                while (grade < 0 || grade > 10) {
                    System.out.print("Digite a nota " + (j + 1) + " (0 a 10): ");
                    grade = scanner.nextDouble();

                    if (grade < 0 || grade > 10) {
                        System.out.println("Erro: Nota inválida! A nota deve estar entre 0 e 10. Por favor, repreencha.");
                    }
                }
                notaAtual[j] = grade;
            }
            
            // Atribui o vetor de notas ao aluno e salva o aluno no vetor principal
            estudante.setNota(notaAtual);
            estudantes[i] = estudante;
        }

        // Exibição dos resultados finais
        System.out.println("\n=================================");
        System.out.println(" RESULTADOS FINAIS");
        System.out.println("=================================");

        for (Estudante estudante : estudantes) {
            System.out.println("Aluno: " + estudante.getNome() + " | Matrícula: " + estudante.getId());
            
            System.out.print("Notas: ");
            double[] notas = estudante.getNota();
            for (int i = 0; i < notas.length; i++) {
                System.out.print(notas[i]);
                if (i < notas.length - 1) {
                    System.out.print(" - ");
                }
            }
            
            System.out.printf("\nMédia Final: %.2f\n", estudante.calcularMedia());
            System.out.println("---------------------------------");
        }

        scanner.close();
    }
}
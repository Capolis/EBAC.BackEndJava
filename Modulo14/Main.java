import java.util.Scanner;

public class Main {
    // Vetor fixo com capacidade para 10 reservas e contador de controle
    private static Reserva[] reservas = new Reserva[10];
    private static int contadorReservas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Laço de repetição do Menu Principal
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza de buffer do teclado

            switch (opcao) {
                case 1 -> cadastrarReserva(scanner);
                case 2 -> listarReservas();
                case 3 -> buscarReservaPorNome(scanner);
                case 4 -> ordenarReservasDecrescente();
                case 5 -> System.out.println("\nSaindo do sistema... Até logo!");
                default -> System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    // Método auxiliar para exibição do menu
    private static void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("  SISTEMA DE RESERVAS DE HOTEL - MENU   ");
        System.out.println("========================================");
        System.out.println("1. Nova reserva");
        System.out.println("2. Listar reservas");
        System.out.println("3. Buscar reserva por nome do hóspede");
        System.out.println("4. Ordenar reservas por dias (decrescente)");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método auxiliar para cadastro e validação
    private static void cadastrarReserva(Scanner scanner) {
        if (contadorReservas >= 10) {
            System.out.println("\nErro: Capacidade máxima de 10 reservas atingida.");
            return;
        }

        System.out.println("\n--- NOVO CADASTRO ---");
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo do quarto (Standard, Luxo, Presidencial): ");
        String tipo = scanner.nextLine();

        System.out.print("Número de dias de estadia: ");
        int dias = scanner.nextInt();
        while (dias < 1) {
            System.out.print("Quantidade inválida! Digite um número de dias maior ou igual a 1: ");
            dias = scanner.nextInt();
        }

        System.out.print("Valor da diária: R$ ");
        double valor = scanner.nextDouble();
        while (valor <= 0) {
            System.out.print("Valor inválido! Digite um valor de diária maior que zero: R$ ");
            valor = scanner.nextDouble();
        }
        scanner.nextLine(); // Limpeza de buffer

        // Instanciação e armazenamento no vetor utilizando o contador como índice
        reservas[contadorReservas] = new Reserva(nome, tipo, dias, valor);
        contadorReservas++;

        System.out.println("\nReserva cadastrada com sucesso!");
    }

    // Método auxiliar para listagem
    private static void listarReservas() {
        if (contadorReservas == 0) {
            System.out.println("\nNenhuma reserva registrada no momento.");
            return;
        }

        System.out.println("\n--- LISTAGEM DE RESERVAS ---");
        for (int i = 0; i < contadorReservas; i++) {
            // Chamada explícita ao método toString() da classe Reserva
            System.out.println((i + 1) + ". " + reservas[i].toString());
        }
    }

    // Método auxiliar para busca parcial case-insensitive
    private static void buscarReservaPorNome(Scanner scanner) {
        if (contadorReservas == 0) {
            System.out.println("\nNão existem reservas para realizar buscas.");
            return;
        }

        System.out.print("\nDigite o nome (ou parte dele) para buscar: ");
        String termoBusca = scanner.nextLine().toLowerCase();
        boolean encontrado = false;

        System.out.println("\n--- RESULTADO DA BUSCA ---");
        for (int i = 0; i < contadorReservas; i++) {
            if (reservas[i].getNomeHospede().toLowerCase().contains(termoBusca)) {
                System.out.println(reservas[i].toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum hóspede encontrado com o termo informado.");
        }
    }

    // Método auxiliar para ordenação decrescente usando o Bubble Sort
    private static void ordenarReservasDecrescente() {
        if (contadorReservas < 2) {
            System.out.println("\nQuantidade de reservas insuficiente para ordenação (mínimo 2).");
            return;
        }

        // Algoritmo de ordenação baseado na quantidade de dias (decrescente)
        for (int i = 0; i < contadorReservas - 1; i++) {
            for (int j = 0; j < contadorReservas - i - 1; j++) {
                if (reservas[j].getNumeroDias() < reservas[j + 1].getNumeroDias()) {
                    // Troca de posições (Variável temporária em português)
                    Reserva temp = reservas[j];
                    reservas[j] = reservas[j + 1];
                    reservas[j + 1] = temp;
                }
            }
        }

        System.out.println("\nReservas ordenadas com sucesso por número de dias (decrescente)!");
        listarReservas(); // Exibe a lista logo após ordenar para validação visual
    }
}
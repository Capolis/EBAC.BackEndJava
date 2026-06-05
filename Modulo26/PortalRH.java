import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortalRH {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner leitor = new Scanner(System.in);
    private static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
    private static int idCounter = 0; // Contador para gerar IDs únicos

    public static void main(String[] args) {

        iniciarMenu();

    }
    
    private static void iniciarMenu() {
        System.out.println("==================================================");
        System.out.println("        BEM-VINDO AO PORTAL DE RH                 ");
        System.out.println("==================================================");
    
        int opcao = 0;
        do {
            System.out.println("\nEscolha uma ação:");
            System.out.println("1 - Criar novo funcionário");
            System.out.println("2 - Remover funcionário");
            System.out.println("3 - Registrar horas de trabalho");
            System.out.println("4 - Listar registros de um funcionário");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            
            try {
                opcao = Integer.parseInt(leitor.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }
    
            switch (opcao) {
                case 1 -> criarFuncionario();
                case 2 -> removerFuncionario();
                case 3 -> registrarHoras();
                case 4 -> listarRegistros();
                case 5 -> System.out.println("Encerrando o portal. Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void criarFuncionario() {
                System.out.print("Digite o nome: ");
        String nome = leitor.nextLine();

        System.out.println("Selecione o tipo de funcionário:");
        System.out.println("1-Gerente | 2-Coordenador | 3-Analista | 4-Assistente | 5-Estagiario");
        int opcaoTipo = Integer.parseInt(leitor.nextLine());
        
        TipoFuncionario tipo;
        switch (opcaoTipo) {
            case 1 -> tipo = TipoFuncionario.GERENTE;
            case 2 -> tipo = TipoFuncionario.COORDENADOR;
            case 3 -> tipo = TipoFuncionario.ANALISTA;
            case 4 -> tipo = TipoFuncionario.ASSISTENTE;
            case 5 -> tipo = TipoFuncionario.ESTAGIARIO;
            default -> {
                System.out.println("Tipo inválido. Operação cancelada.");
                return;
            }
        }
        int id = idCounter++; // Gera um ID único para cada funcionário

        funcionarios.add(new Funcionario(id, nome, tipo));
        System.out.println("Funcionário cadastrado com sucesso! Dados: " + funcionarios.get(funcionarios.size() - 1).toString());
    }

    private static void removerFuncionario() {
        System.out.print("Digite o ID do funcionário que deseja remover: ");
        int id = Integer.parseInt(leitor.nextLine());
        
        Funcionario f = buscarPorId(id);
        if (f != null) {
            funcionarios.remove(f);
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void registrarHoras() {
        System.out.print("Digite o ID do funcionário: ");
        int id = Integer.parseInt(leitor.nextLine());
        Funcionario f = buscarPorId(id);

        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        // Validação 1: Barrar cargos que não batem ponto
        if (!f.getTipo().isPodeBaterPonto()) {
            System.out.println("Ação bloqueada: Funcionários do tipo " + f.getTipo() + " não batem ponto.");
            return;
        }

        try {
            System.out.print("Data do registro (DD/MM/AAAA): ");
            LocalDate data = LocalDate.parse(leitor.nextLine(), formatadorData);

            System.out.print("Horário de entrada (HH:MM): ");
            LocalTime entrada = LocalTime.parse(leitor.nextLine(), formatadorHora);

            System.out.print("Horário de saída (HH:MM): ");
            LocalTime saida = LocalTime.parse(leitor.nextLine(), formatadorHora);

            // Validação 2: Saída igual ou anterior à entrada
            if (!saida.isAfter(entrada)) {
                System.out.println("Erro de Registro: O horário de saída deve ser posterior ao horário de entrada.");
                return;
            }

            // Validação 3: Limites de horários operacionais
            if (entrada.isBefore(LocalTime.of(6, 0))) {
                System.out.println("Erro de Registro: Não é permitido registrar entrada antes das 06:00h.");
                return;
            }
            if (saida.isAfter(LocalTime.of(22, 0))) {
                System.out.println("Erro de Registro: Não é permitido registrar saída após as 22:00h.");
                return;
            }

            // Cálculo para validação de horas extras (Assumindo jornada base de 8 horas diárias)
            long minutosTotais = Duration.between(entrada, saida).toMinutes();
            long minutosTrabalhados = minutosTotais - 60; // Desconta 1 hora de almoço
            
            // Impede registros com menos de 1 hora de diferença total (para evitar minutos negativos)
            if (minutosTrabalhados < 0) {
                System.out.println("Erro: Tempo insuficiente para justificar a hora de almoço padrão.");
                return;
            }

            long jornadaBaseMinutos = 8 * 60; 
            long minutosExtras = minutosTrabalhados - jornadaBaseMinutos;
            
            // Validação 4: Limite de horas extras por tipo
            if (minutosExtras > f.getTipo().getLimiteHorasExtras() * 60) {
                System.out.println("Erro de Registro: Limite de horas extras excedido.");
                System.out.println("O cargo " + f.getTipo() + " permite no máximo " + f.getTipo().getLimiteHorasExtras() + " horas extras.");
                return;
            }

            f.adicionarRegistro(new RegistroPonto(data, entrada, saida));
            System.out.println("Ponto registrado com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Erro de formatação. Utilize os padrões DD/MM/AAAA e HH:MM.");
        }
    }

    private static void listarRegistros() {
        System.out.print("Digite o ID do funcionário: ");
        int id = Integer.parseInt(leitor.nextLine());
        Funcionario f = buscarPorId(id);

        if (f == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("\n--- Registros de Ponto: " + f.getNome() + " (" + f.getTipo() + ") ---");
        if (!f.getTipo().isPodeBaterPonto()) {
            System.out.println("Ação bloqueada: Funcionários do tipo " + f.getTipo() + " não batem ponto.");
        }
        else if (f.getRegistros().isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (RegistroPonto rp : f.getRegistros()) {
                System.out.println(rp.toString());
            }
        }
        System.out.println("---------------------------------------------------");
    }

    private static Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }
}
import java.util.List;
import java.util.Scanner;

public class AplicacaoBiblioteca {

    public static void main(String[] args) {
        GerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
        biblioteca.carregarDados(); // Carrega dados ao iniciar
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 10) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.println("\nCadastrar Livro");
                    System.out.print("Título: ");
                    String tituloLivro = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autorLivro = scanner.nextLine();
                    System.out.print("Ano de Publicação: ");
                    try{
                        int ano = Integer.parseInt(scanner.nextLine());
                        biblioteca.adicionarLivro(new Livro(tituloLivro, autorLivro, ano));
                        System.out.println("Livro cadastrado com sucesso.");
                        biblioteca.salvarDados(); // Salva após o cadastro
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Ano de publicação deve ser um número válido.");
                    }
                }
                case 2 -> {
                    System.out.println("\nCadastrar Usuário");
                    System.out.print("Nome: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();
                    try {
                        biblioteca.adicionarUsuario(new Usuario(nomeUsuario, email));
                        System.out.println("Usuário cadastrado com sucesso.");
                        biblioteca.salvarDados(); // Salva após o cadastro
                    } catch (UsuarioCadastradoException e) {
                        System.out.println(e.getMessage()); // Trata a exceção de e-mail duplicado
                    }
                }
                case 3 -> {
                    System.out.println("\nListar Livros");
                    // Opcional: ordenar antes de listar, o método esta ativo, só remover o comentário para ordenar por título
                    // biblioteca.ordenarLivrosPorTitulo();
                    List<Livro> todosOsLivros = biblioteca.getLivros();
                    if (todosOsLivros.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                    } else {
                        todosOsLivros.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.println("\nListar Usuários");
                    List<Usuario> todosOsUsuarios = biblioteca.getUsuarios();
                    if (todosOsUsuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        todosOsUsuarios.forEach(System.out::println);
                    }
                }
                case 5 -> {
                    System.out.println("\nBuscar Livro pelo Título");
                    System.out.print("Digite o título completo: ");
                    String tituloBusca = scanner.nextLine();
                    biblioteca.buscarLivroPorTitulo(tituloBusca)
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Livro não encontrado.")
                            );
                }
                case 6 -> {
                    System.out.println("\nBuscar Livro pelo Autor");
                    System.out.print("Digite o nome do autor: ");
                    String autorBusca = scanner.nextLine();
                    List<Livro> livrosDoAutor = biblioteca.buscarLivrosPorAutor(autorBusca);
                    if (livrosDoAutor.isEmpty()) {
                        System.out.println("Nenhum livro encontrado para este autor.");
                    } else {
                        livrosDoAutor.forEach(System.out::println);
                    }
                }
                case 7 -> {
                    System.out.println("\nBuscar Usuário pelo E-mail");
                    System.out.print("Digite o e-mail: ");
                    String emailBusca = scanner.nextLine();
                    biblioteca.buscarUsuarioPorEmail(emailBusca)
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Usuário não encontrado.")
                            );
                }
                case 8 -> {
                    System.out.println("\nEmprestar Livro");
                    System.out.print("Título do Livro: ");
                    String tituloEmp = scanner.nextLine();
                    System.out.print("E-mail do Usuário: ");
                    String emailEmp = scanner.nextLine();
                    try {
                        biblioteca.emprestarLivro(tituloEmp, emailEmp);
                        biblioteca.salvarDados(); // Salva após a alteração de status
                    } catch (LivroNaoExisteException | LivroIndisponivelException | UsuarioNaoCadastradoException e) {
                        System.out.println(e.getMessage()); // Trata as exceções
                    }
                }
                case 9 -> {
                    System.out.println("\nDevolver Livro");
                    System.out.print("Título do Livro: ");
                    String tituloDev = scanner.nextLine();
                    try {
                        biblioteca.devolverLivro(tituloDev);
                        biblioteca.salvarDados(); // Salva após a alteração de status
                    } catch (LivroNaoExisteException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 10 -> {
                    System.out.println("\nSaindo do sistema. Até logo!");
                    biblioteca.salvarDados(); // Garante o salvamento ao sair
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    // Exibe o menu principal
    private static void exibirMenu() {
        System.out.println("");
        System.out.println("\n Menu Principal");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Cadastrar usuário");
        System.out.println("3 - Listar livros");
        System.out.println("4 - Listar usuários");
        System.out.println("5 - Buscar livro pelo título");
        System.out.println("6 - Buscar livro pelo autor");
        System.out.println("7 - Buscar usuário pelo e-mail");
        System.out.println("8 - Emprestar livro");
        System.out.println("9 - Devolver livro");
        System.out.println("10 - Sair do sistema");
        System.out.println("");
    }
}
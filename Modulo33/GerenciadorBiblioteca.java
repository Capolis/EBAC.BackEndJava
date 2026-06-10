import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class GerenciadorBiblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private static final String NOME_ARQUIVO = "dados_biblioteca.dat";

    public GerenciadorBiblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    // Métodos de Cadastro e Listagem
    public void adicionarLivro(Livro novoLivro) {
        this.livros.add(novoLivro);
    }

    public void adicionarUsuario(Usuario novoUsuario) throws UsuarioCadastradoException {
        // Regra de negócio: E-mail deve ser único
        Optional<Usuario> usuarioExistente = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(novoUsuario.getEmail()))
                .findFirst();
        if (usuarioExistente.isPresent()) {
            throw new UsuarioCadastradoException("Erro: Um usuário com o e-mail '" + novoUsuario.getEmail() + "' já está cadastrado.");
        }
        this.usuarios.add(novoUsuario);
    }
    
    // Métodos de busca por título usando recursos de stream/lambda
    public Optional<Livro> buscarLivroPorTitulo(String tituloLivro) {
        return livros.stream()
        .filter(livro -> livro.getTitulo().equalsIgnoreCase(tituloLivro))
        .findFirst();
    }
    
    public List<Livro> buscarLivrosPorAutor(String autorLivro) {
        return livros.stream()
        .filter(livro -> livro.getAutor().equalsIgnoreCase(autorLivro))
        .collect(Collectors.toList());
    }
    
    public Optional<Usuario> buscarUsuarioPorEmail(String emailUsuario) {
        // Uso obrigatório de Programação Funcional (Streams e Lambdas)
        return usuarios.stream()
        .filter(usuario -> usuario.getEmail().equalsIgnoreCase(emailUsuario))
        .findFirst();
    }
    
    // Métodos de ordenação e agrupamento usando recursos de Comparator/GroupingBy
    public void ordenarLivrosPorTitulo() {
        this.livros.sort((livro1, livro2) -> livro1.getTitulo().compareToIgnoreCase(livro2.getTitulo()));
    }
    
    // Método de ordenação por ano de publicação
    public void ordenarLivrosPorAno() {
        this.livros.sort((livro1, livro2) -> Integer.compare(livro1.getAnoPublicacao(), livro2.getAnoPublicacao()));
    }
    
    // Método de agrupamento por autor
    public Map<String, List<Livro>> agruparLivrosPorAutor() {
        return livros.stream()
        .collect(Collectors.groupingBy(Livro::getAutor));
    }
    
    // Métodos de Empréstimo e Devolução
    public void emprestarLivro(String titulo, String emailUsuario) 
    throws LivroNaoExisteException, LivroIndisponivelException, UsuarioNaoCadastradoException {
        
        Livro livroProcurado = buscarLivroPorTitulo(titulo)
        .orElseThrow(() -> new LivroNaoExisteException("Erro: Livro '" + titulo + "' não existe no acervo."));
        
        if (livroProcurado.estaEmprestado()) {
            throw new LivroIndisponivelException("Erro: Livro '" + titulo + "' já está emprestado.");
        }
        
        Usuario usuarioProcurado = buscarUsuarioPorEmail(emailUsuario)
        .orElseThrow(() -> new UsuarioNaoCadastradoException("Erro: Usuário com e-mail '" + emailUsuario + "' não cadastrado."));
        
        livroProcurado.setEmprestado(true);
        System.out.println("Livro '" + titulo + "' emprestado com sucesso para " + usuarioProcurado.getNome() + ".");
    }
    
    public void devolverLivro(String titulo) throws LivroNaoExisteException {
        Livro livroProcurado = buscarLivroPorTitulo(titulo)
        .orElseThrow(() -> new LivroNaoExisteException("Erro: Livro '" + titulo + "' não existe no acervo."));
        
        if (!livroProcurado.estaEmprestado()) {
            System.out.println("Aviso: O livro '" + titulo + "' não estava emprestado.");
            return;
        }
        
        livroProcurado.setEmprestado(false);
        System.out.println("Livro '" + titulo + "' devolvido com sucesso.");
    }
    
    // Método de salvamento de dados usando Serialização
    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            out.writeObject(this.livros);
            out.writeObject(this.usuarios);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
    // Método de carregamento de dados usando Serialização
    @SuppressWarnings("unchecked")
    public void carregarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de dados não encontrado. Iniciando com dados vazios.");
            return;
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            this.livros = (List<Livro>) in.readObject();
            this.usuarios = (List<Usuario>) in.readObject();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
    
        public List<Livro> getLivros() {
            return this.livros;
        }
    
        public List<Usuario> getUsuarios() {
            return this.usuarios;
        }
}
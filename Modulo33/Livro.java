// O uso da variável serialVersionUID é para garantir a compatibilidade entre diferentes versões da classe 
// durante o processo de serialização e desserialização, ajudando a evitar problemas de incompatibilidade 
// quando a estrutura da classe é alterada, como adição ou remoção de campos.
// Se a serialVersionUID não for definido, o Java gerará um automaticamente com base na estrutura da classe, 
// o que pode levar a erros de desserialização se a classe for modificada.
// Portanto, é uma boa prática definir explicitamente o serialVersionUID para garantir que as versões futuras 
// da classe possam ser desserializadas corretamente, mesmo após alterações na estrutura da classe.
import java.io.Serializable;

class Livro implements Serializable {
    private static final long serialVersionUID = 1L; // Necessário para a serialização
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean estaEmprestado;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.estaEmprestado = false; // Começa como disponível
    }
    
    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + autor + " | Ano: " + anoPublicacao + 
        " | Status: " + (estaEmprestado ? "Emprestado" : "Disponível");
    }
    
    // Getters e Setters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setEmprestado(boolean status) { this.estaEmprestado = status; }
    public boolean estaEmprestado() { return estaEmprestado; }
}
// O uso da variável serialVersionUID é para garantir a compatibilidade entre diferentes versões da classe 
// durante o processo de serialização e desserialização, ajudando a evitar problemas de incompatibilidade 
// quando a estrutura da classe é alterada, como adição ou remoção de campos.
// Se a serialVersionUID não for definido, o Java gerará um automaticamente com base na estrutura da classe, 
// o que pode levar a erros de desserialização se a classe for modificada.
// Portanto, é uma boa prática definir explicitamente o serialVersionUID para garantir que as versões futuras 
// da classe possam ser desserializadas corretamente, mesmo após alterações na estrutura da classe.
import java.io.Serializable;

class Usuario implements Serializable {
    private static final long serialVersionUID = 1L; // Necessário para a serialização
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Usuário: " + nome + " | E-mail: " + email;
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}
// Exceção lançada quando se tenta cadastrar um usuário com um e-mail que já existe.
class UsuarioCadastradoException extends Exception {
    public UsuarioCadastradoException(String mensagem) {
        super(mensagem);
    }
}
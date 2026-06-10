// Exceção lançada quando um usuário procurado (ex: por e-mail) não é encontrado.
class UsuarioNaoCadastradoException extends Exception {
    public UsuarioNaoCadastradoException(String mensagem) {
        super(mensagem);
    }
}
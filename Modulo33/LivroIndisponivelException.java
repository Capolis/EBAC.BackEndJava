// Exceção lançada quando se tenta emprestar um livro que já está emprestado.
class LivroIndisponivelException extends Exception {
    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
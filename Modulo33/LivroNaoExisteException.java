// Exceção lançada quando um livro procurado não é encontrado no acervo.
class LivroNaoExisteException extends Exception {
    public LivroNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
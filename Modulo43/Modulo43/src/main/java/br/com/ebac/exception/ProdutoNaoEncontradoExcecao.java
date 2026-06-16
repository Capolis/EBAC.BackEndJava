package br.com.ebac.exception;

public class ProdutoNaoEncontradoExcecao extends RuntimeException {
    
    public ProdutoNaoEncontradoExcecao(String mensagem) {
        super(mensagem);
    }
}
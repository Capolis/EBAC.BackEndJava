package br.com.ebac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipuladorGlobalDeExcecoes {

    @ExceptionHandler(ProdutoNaoEncontradoExcecao.class)
    public ResponseEntity<String> lidarComProdutoNaoEncontrado(ProdutoNaoEncontradoExcecao excecao) {
        // Retorna o status 404 (Not Found) junto com a mensagem do erro
        return new ResponseEntity<>(excecao.getMessage(), HttpStatus.NOT_FOUND);
    }
}
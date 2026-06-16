package br.com.ebac.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.model.Produto;
import br.com.ebac.service.ProdutoService;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    // O Spring injeta o serviço aqui automaticamente
    private final ProdutoService servicoDeProduto;

    public ProdutoController(ProdutoService servicoDeProduto) {
        this.servicoDeProduto = servicoDeProduto;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return servicoDeProduto.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorIdentificador(@PathVariable Integer id) {
        return servicoDeProduto.buscarPorIdentificador(id);
    }

    @PostMapping
    public Produto adicionarNovo(@RequestBody Produto novoProduto) {
        return servicoDeProduto.adicionarNovo(novoProduto);
    }

    @PatchMapping("/{id}")
    public Produto atualizarExistente(@PathVariable Integer id, @RequestBody Produto informacoesAtualizadas) {
        return servicoDeProduto.atualizarExistente(id, informacoesAtualizadas);
    }

    @DeleteMapping("/{id}")
    public void removerExistente(@PathVariable Integer id) {
        servicoDeProduto.removerExistente(id);
    }
}
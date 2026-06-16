package br.com.ebac.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ebac.exception.ProdutoNaoEncontradoExcecao;
import br.com.ebac.model.Produto;

@Service
public class ProdutoService {

    private static final List<Produto> listaDeProdutos = new ArrayList<>();
    private static Integer contadorDeIdentificador = 1;

    static {
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Mouse Óptico", 44.99));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Teclado Mecânico", 299.99));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Monitor 24 Polegadas", 849.99));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Headset Gamer", 180.00));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Cadeira Ergonômica", 1200.00));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Webcam Full HD", 250.00));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Carregador Portátil", 99.99));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Caixa de Som Bluetooth", 150.00));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "Roteador Wi-Fi 6", 449.99));
        listaDeProdutos.add(new Produto(contadorDeIdentificador++, "SSD 1TB NVMe", 380.01));
    }

    public List<Produto> listarTodos() {
        return listaDeProdutos;
    }

    public Produto buscarPorIdentificador(Integer id) {
        for (Produto produtoAtual : listaDeProdutos) {
            if (produtoAtual.getId().equals(id)) {
                return produtoAtual;
            }
        }
        // Dispara o erro que criamos se o ID não existir
        throw new ProdutoNaoEncontradoExcecao("Produto com o identificador " + id + " não foi encontrado.");
    }

    public Produto adicionarNovo(Produto novoProduto) {
        novoProduto.setId(contadorDeIdentificador++);
        listaDeProdutos.add(novoProduto);
        return novoProduto;
    }

    public Produto atualizarExistente(Integer id, Produto informacoesAtualizadas) {
        // Aproveitamos o método de busca, que já valida se existe ou não
        Produto produtoAtual = buscarPorIdentificador(id);
        
        if (informacoesAtualizadas.getNome() != null) {
            produtoAtual.setNome(informacoesAtualizadas.getNome());
        }
        if (informacoesAtualizadas.getPreco() != null) {
            produtoAtual.setPreco(informacoesAtualizadas.getPreco());
        }
        return produtoAtual;
    }

    public void removerExistente(Integer id) {
        Produto produtoAtual = buscarPorIdentificador(id);
        listaDeProdutos.remove(produtoAtual);
    }
}
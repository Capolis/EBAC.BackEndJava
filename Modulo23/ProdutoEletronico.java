// Em vez de herdar produto por produto, criei uma categoria geral que estende a classe base
class ProdutoEletronico extends Produto {
    
    public ProdutoEletronico(TipoProduto tipo) {
        super(tipo); // Chama o construtor da classe abstrata Produto
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Item: " + tipo.getNome() + " | Valor: R$ " + tipo.getPreco());
    }
}
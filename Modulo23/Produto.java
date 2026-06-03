// Classe abstrata principal
abstract class Produto {
    // Protected para que a classe filha possa acessar, e final para não ser alterado
    protected final TipoProduto tipo;

    public Produto(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public double getPreco() {
        return tipo.getPreco();
    }

    // Método abstrato para exibir detalhes do produto, cada tipo pode ter uma implementação diferente
    public abstract void exibirDetalhes();
}
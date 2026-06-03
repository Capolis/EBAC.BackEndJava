// Enumeração para guardar os tipos de produtos, nomes e preços fixos
enum TipoProduto {
    TELEVISAO("Televisão", 1500.00),
    RADIO("Rádio", 150.00),
    VIDEOGAME("Videogame", 3500.00),
    TABLET("Tablet", 1200.00),
    CELULAR("Celular", 2500.00);

    // Variáveis que não irão ser alteradas, por isso são final
    private final String nome;
    private final double preco;

    TipoProduto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
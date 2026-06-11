package SOLID;

public class Fatura {
    private final double valorTotal;

    public Fatura(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double obterValorTotal() {
        return valorTotal;
    }
}
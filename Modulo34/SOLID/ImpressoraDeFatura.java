package SOLID;

public class ImpressoraDeFatura {
    public void imprimir(SOLID.Fatura fatura) {
        System.out.println("Imprimindo fatura no valor de: R$ " + fatura.obterValorTotal());
    }
}
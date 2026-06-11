import BUILDER.Casa;
import BUILDER.ConstrutorDeCasa;
import FACTORY_E_STRATEGY.EstrategiaDeFrete;
import FACTORY_E_STRATEGY.FabricaDeFrete;
import SOLID.Fatura;
import SOLID.ImpressoraDeFatura;
import java.util.Scanner;

public class Main {
    public static void main(String[] argumentos) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. TESTANDO SOLID");
        Fatura minhaFatura = new Fatura(250.0);
        ImpressoraDeFatura impressora = new ImpressoraDeFatura();
        impressora.imprimir(minhaFatura);

        System.out.println("\n2. TESTANDO BUILDER");
        Casa minhaCasa = new ConstrutorDeCasa()
                .definirParedes("Alvenaria e Tijolos")
                .definirTelhado("Telhas Coloniais")
                .adicionarPiscina(true)
                .construir();
            
        minhaCasa.exibirDetalhes();

        System.out.println("\n3. TESTANDO FACTORY + STRATEGY");
        String escolhaDeFrete = "expresso";
        
        double pesoDoProduto = 10.0; // Simulando um pacote de 10 kg
        FabricaDeFrete fabrica = new FabricaDeFrete();
        
        try {
            EstrategiaDeFrete estrategia = fabrica.criarEstrategia(escolhaDeFrete);
            double valorDoFrete = estrategia.calcularFrete(pesoDoProduto);            
            System.out.println("O valor do frete " + escolhaDeFrete + " para " + pesoDoProduto + " kg é: R$ " + valorDoFrete);
        } catch (IllegalArgumentException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }

        scanner.close();
    }
}
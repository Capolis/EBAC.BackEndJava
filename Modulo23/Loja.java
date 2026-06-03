import java.util.Scanner;

public class Loja {
    // Atributos da classe Loja para acesso global pelos métodos
    private Scanner scanner = new Scanner(System.in);
    private Produto[] carrinho = new Produto[100];
    private int totalItens = 0;

    public static void main(String[] args) {

        Loja minhaLoja = new Loja();
        minhaLoja.iniciarSistema();

    }

    // Método que controla o fluxo principal do programa
    public void iniciarSistema() {
        exibirBoasVindas();
        realizarCompras();
        exibirResumoCompra();
        scanner.close();
    }

    private void exibirBoasVindas() {
        System.out.println("======================================");
        System.out.println("   Seja bem-vindo(a) à nossa loja!    ");
        System.out.println("======================================");
    }

    // Método que gerencia o loop de compras
    private void realizarCompras() {
        boolean comprando = true;

        while (comprando) {
            exibirCatalogo();
            int escolha = lerEntradaInteira("Digite o número do produto: ");

            if (escolha == -1)
                continue; // Volta para o início do loop se a entrada foi texto

            if (escolha == 0) {
                comprando = false;
                continue;
            }

            if (escolha < 1 || escolha > 5) {
                System.out.println("-> Produto não encontrado no catálogo.");
                continue;
            }

            int quantidade = lerEntradaInteira("Quantas unidades você deseja? ");

            if (quantidade == -1)
                continue;

            if (quantidade <= 0) {
                System.out.println("-> A quantidade precisa ser maior que zero.");
                continue;
            }

            if (totalItens + quantidade > carrinho.length) {
                System.out.println("-> O carrinho está cheio! Finalize a compra atual.");
                continue;
            }

            adicionarProdutos(escolha, quantidade);
        }
    }

    private void exibirCatalogo() {
        System.out.println("\nCatálogo de Produtos:");
        System.out.println("1 - Televisão (R$ 1500.00)");
        System.out.println("2 - Rádio (R$ 150.00)");
        System.out.println("3 - Videogame (R$ 3500.00)");
        System.out.println("4 - Tablet (R$ 1200.00)");
        System.out.println("5 - Celular (R$ 2500.00)");
        System.out.println("0 - Finalizar Compra");
    }

    // Método focado apenas em ler os números e tratar a entrada inválida
    private int lerEntradaInteira(String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextInt()) {
            System.out.println("-> Entrada inválida! Digite apenas números.");
            scanner.next(); // Limpa o buffer
            return -1; // Retorna um valor inválido para o loop tratar
        }
        return scanner.nextInt();
    }

    // Método responsável apenas por instanciar e guardar no vetor
    private void adicionarProdutos(int escolha, int quantidade) {
        TipoProduto tipoEscolhido = null;
        switch (escolha) {
            case 1 -> tipoEscolhido = TipoProduto.TELEVISAO;
            case 2 -> tipoEscolhido = TipoProduto.RADIO;
            case 3 -> tipoEscolhido = TipoProduto.VIDEOGAME;
            case 4 -> tipoEscolhido = TipoProduto.TABLET;
            case 5 -> tipoEscolhido = TipoProduto.CELULAR;
        }

        for (int i = 0; i < quantidade; i++) {
            carrinho[totalItens] = new ProdutoEletronico(tipoEscolhido);
            totalItens++;
        }

        System.out.println("-> " + quantidade + "x " + tipoEscolhido.getNome() + " adicionado(s) com sucesso!");
    }

    // Método que calcula e exibe os totais
    private void exibirResumoCompra() {
        System.out.println("\n======================================");
        System.out.println("          Resumo da sua Compra        ");
        System.out.println("======================================");

        if (totalItens == 0) {
            System.out.println("Você não comprou nada. Volte sempre!");
            return;
        }

        double valorFinal = 0.0;
        int qtdTv = 0, qtdRadio = 0, qtdVg = 0, qtdTablet = 0, qtdCelular = 0;

        for (int i = 0; i < totalItens; i++) {
            Produto p = carrinho[i];
            valorFinal += p.getPreco();

            switch (p.getTipo()) {
                case TELEVISAO -> qtdTv++;
                case RADIO -> qtdRadio++;
                case VIDEOGAME -> qtdVg++;
                case TABLET -> qtdTablet++;
                case CELULAR -> qtdCelular++;
            }
        }

        System.out.println("Lista de Itens:");
        if (qtdTv > 0)
            System.out.println("- Televisão: " + qtdTv + " unidade(s)");
        if (qtdRadio > 0)
            System.out.println("- Rádio: " + qtdRadio + " unidade(s)");
        if (qtdVg > 0)
            System.out.println("- Videogame: " + qtdVg + " unidade(s)");
        if (qtdTablet > 0)
            System.out.println("- Tablet: " + qtdTablet + " unidade(s)");
        if (qtdCelular > 0)
            System.out.println("- Celular: " + qtdCelular + " unidade(s)");

        System.out.printf("\nValor Total: R$ %.2f\n", valorFinal);
    }
}
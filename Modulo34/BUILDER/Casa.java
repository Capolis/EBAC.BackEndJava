package BUILDER;

public class Casa {
    private final String paredes;
    private final String telhado;
    private boolean temPiscina;

    public Casa(ConstrutorDeCasa construtor) {
        this.paredes = construtor.paredes;
        this.telhado = construtor.telhado;
        this.temPiscina = construtor.temPiscina;
    }

    public void exibirDetalhes() {
        String detalhePiscina = temPiscina ? " e com piscina luxuosa." : " e sem piscina.";
        System.out.println("Casa construída com paredes de " + paredes + ", telhado de " + telhado + detalhePiscina);
    }
    
}
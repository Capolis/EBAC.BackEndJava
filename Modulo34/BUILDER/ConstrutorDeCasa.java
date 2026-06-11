package BUILDER;

public class ConstrutorDeCasa {
    protected String paredes;
    protected String telhado;
    protected boolean temPiscina;

    public ConstrutorDeCasa definirParedes(String paredes) {
        this.paredes = paredes;
        return this;
    }

    public ConstrutorDeCasa definirTelhado(String telhado) {
        this.telhado = telhado;
        return this;
    }

    public ConstrutorDeCasa adicionarPiscina(boolean temPiscina) {
        this.temPiscina = temPiscina;
        return this;
    }

    public Casa construir() {
        return new Casa(this);
    }
}
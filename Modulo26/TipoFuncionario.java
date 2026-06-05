public enum TipoFuncionario {
    GERENTE(false, 0),
    COORDENADOR(true, 5),
    ANALISTA(true, 3),
    ASSISTENTE(true, 3),
    ESTAGIARIO(false, 0);

    private final boolean podeBaterPonto;
    private final int limiteHorasExtras;

    TipoFuncionario(boolean podeBaterPonto, int limiteHorasExtras) {
        this.podeBaterPonto = podeBaterPonto;
        this.limiteHorasExtras = limiteHorasExtras;
    }

    public boolean isPodeBaterPonto() {
        return podeBaterPonto;
    }

    public int getLimiteHorasExtras() {
        return limiteHorasExtras;
    }
}
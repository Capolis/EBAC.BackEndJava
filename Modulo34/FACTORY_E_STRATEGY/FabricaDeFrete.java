package FACTORY_E_STRATEGY;

public class FabricaDeFrete {
    public EstrategiaDeFrete criarEstrategia(String tipoDeFrete) {
        if (tipoDeFrete.equalsIgnoreCase("expresso")) {
            return new FreteExpresso();
        } else if (tipoDeFrete.equalsIgnoreCase("padrao")) {
            return new FretePadrao();
        }
        throw new IllegalArgumentException("Tipo de frete desconhecido: " + tipoDeFrete);
    }
}
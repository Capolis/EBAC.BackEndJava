package FACTORY_E_STRATEGY;

public class FreteExpresso implements EstrategiaDeFrete {
    @Override
    public double calcularFrete(double peso) {
        return peso * 15.0; // R$ 15 por kg
    }
}

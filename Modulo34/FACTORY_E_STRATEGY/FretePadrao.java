package FACTORY_E_STRATEGY;

public class FretePadrao implements EstrategiaDeFrete {
    @Override
    public double calcularFrete(double peso) {
        return peso * 5.0; // R$ 5 por kg
    }
}
/*
Para esse problema da escada, Climb Stairs, a lógica por trás dos passos é baseada na sequência de Fibonacci: 
Como você só pode dar 1 ou 2 passos por vez, a quantidade de maneiras de chegar ao degrau n é sempre a soma 
das maneiras de chegar ao degrau imediatamente anterior (n-1) com o degrau anterior a ele (n-2), ou seja: 
F(n) = F(n-1) + F(n-2)
*/

public class Escada {
    private int totalDegraus;

    // Construtor
    public Escada(int totalDegraus) {
        this.totalDegraus = totalDegraus;
    }

    // Método principal de cálculo
    public int calcularPossibilidades() {
        // Chama a função recursiva passando o total de degraus
        return calcularRecursivamente(this.totalDegraus);
    }

    // Função recursiva privada
    private int calcularRecursivamente(int degrausRestantes) {
        if (degrausRestantes == 1)
             return 1;        
        if (degrausRestantes == 2) 
            return 2;

        // Passo recursivo: soma as possibilidades dos dois degraus anteriores
        return calcularRecursivamente(degrausRestantes - 1) + calcularRecursivamente(degrausRestantes - 2);
    }

    // MÉTODOS GETTER E SETTER
    public int getTotalDegraus() {
        return totalDegraus;
    }

    public void setTotalDegraus(int totalDegraus) {
        this.totalDegraus = totalDegraus;
    }
}
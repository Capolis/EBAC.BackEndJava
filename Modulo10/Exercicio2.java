// EXERCÍCIO 2

// Receba um vetor de n números inteiros inseridos pelo usuário.
// Percorra o vetor altere o valor de cada posição: se o valor da posição for par, seu novo valor deve ser multiplicado por 2, se for ímpar, o novo valor da posição deve ser ele mesmo elevado por 2.
// Exiba o conteúdo do novo vetor.

import java.util.Arrays;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantos números terá o vetor? ");
        int n = scanner.nextInt();
        int[] vetor = new int[n];

        // Recebendo os dados do usuário
        for (int i = 0; i < n; i++) {
            System.out.println("Digite o valor " + (i + 1) + ": ");
            vetor[i] = scanner.nextInt();
        }

        // Percorrendo o vetor e alterando os valores
        for (int i = 0; i < n; i++) {
            if (vetor[i] % 2 == 0) { // Se for par
                vetor[i] = vetor[i] * 2;
            } else { // Se for ímpar
                // Utilizando Math.pow e convertendo (cast) para int
                vetor[i] = (int) Math.pow(vetor[i], 2); 
            }
        }

        // Exibindo o novo vetor
        System.out.println("Resultado: " + Arrays.toString(vetor));
        scanner.close();
    }
}
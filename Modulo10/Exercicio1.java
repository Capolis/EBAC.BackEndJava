// EXERCÍCIO 1

// Preencha um vetor de n números com valores aleatórios advindos do console.
// Ordene este vetor.
// Em seguida, receba um nome e exiba na tela a quantidade de vogais que esse nome possui.

import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Preencher um vetor de n números
        System.out.println("Digite a quantidade de números do vetor: ");
        int n = scanner.nextInt();
        int[] vetor = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Digite o valor para a posição " + i + ": ");
            vetor[i] = scanner.nextInt();
        }

        // 2. Ordenar o vetor usando Bubble Sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }

        System.out.println("Vetor ordenado: ");
        for (int num : vetor) {
            System.out.println(num + " ");
        }

        // 3. Receber um nome e contar as vogais
        System.out.println("Digite um nome: ");
        String nome = scanner.next();
        int contadorVogais = 0;
        
        // Convertendo para letras minúsculas e transformando em vetor de char
        char[] letras = nome.toLowerCase().toCharArray(); 
        
        for (char letra : letras) {
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                contadorVogais++;
            }
        }
        
        System.out.println("O nome '" + nome + "' possui " + contadorVogais + " vogal(is).");
        scanner.close();
    }
}
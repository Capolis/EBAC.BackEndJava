// EXERCÍCIO 3

// Receba um nome qualquer do usuário.
// Percorra esse nome num vetor e exiba o vetor com o nome invertido.

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um nome: ");
        String nome = scanner.nextLine();

        // Transforma o nome em um vetor de caracteres
        char[] vetorNome = nome.toCharArray();
        int n = vetorNome.length;

        // Percorrendo até a metade do vetor e invertendo as posições
        for (int i = 0; i < n / 2; i++) {
            // Guardando o valor momentâneo em uma variável (dica)
            char temp = vetorNome[i];
            
            // A posição atual recebe a sua posição oposta no final do vetor
            vetorNome[i] = vetorNome[n - 1 - i];
            
            // A posição oposta recebe o valor que estava guardado
            vetorNome[n - 1 - i] = temp;
        }

        // Transformando o vetor de volta para String para exibir
        String nomeInvertido = new String(vetorNome);
        System.out.println("Resultado: " + nomeInvertido.toUpperCase());
        
        scanner.close();
    }
}
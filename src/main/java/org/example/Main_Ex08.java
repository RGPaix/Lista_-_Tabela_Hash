package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;

import java.util.Scanner;

public class Main_Ex08 {
    public static void main(String[] args) {
        intKeyExtractor<Integer> extrator = num -> num;
        CriarHash<Integer> tabelaHash = new CriarHash<>(extrator, 10);

        Integer[] missoes = {
                300,
                302,
                303,
                304,
                306
        };

        System.out.println("=== INSERINDO MISSÕES NA TABELA HASH ===\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de armaduras: " + missoes.length + "\n");

        // Inserir cada missão e mostrar a posição
        for (Integer missao : missoes) {
            int indice = missao % tabelaHash.capacity();

            tabelaHash.inserirSemColisao(missao);

            System.out.println("Missão inserida: " + missao);
            System.out.println("Posição na tabela: [" + indice + "]");
            System.out.println();
        }

        System.out.println("=== ESTATÍSTICAS DA TABELA ===");
        System.out.println(tabelaHash.stats());

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA COMPLETA ===");
        System.out.println(tabelaHash);

        // Ler número do usuário
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              BUSCA DE MISSÃO                           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.print("Digite o número da missão para verificar: ");
        int numeroBusca = scanner.nextInt();

        Integer encontrada = tabelaHash.buscarSemColisao(numeroBusca);

        System.out.println("\nResultado da busca:");
        if (encontrada != null) {
            int indiceMissao = numeroBusca % tabelaHash.capacity();
            System.out.println("✅ Missão " + numeroBusca + " ENCONTRADA!");
            System.out.println("   Posição na tabela: [" + indiceMissao + "]");
        } else {
            System.out.println("❌ Missão " + numeroBusca + " NÃO ENCONTRADA!");
            System.out.println("   Status: NÃO CONCLUÍDA ou NÃO EXISTE");
        }

        scanner.close();
    }
}

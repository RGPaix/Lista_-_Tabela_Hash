package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;

public class Main_Ex10 {
    public static void main(String[] args) {
        intKeyExtractor<Integer> extrator = num -> num;
        CriarHash<Integer> tabelaHash = new CriarHash<>(extrator, 20);

        Integer[] chaves = {
                36777987,
                42336860,
                97646643,
                24349786,
                89879793,
                66287862,
                87364443,
                27784672
        };

        System.out.println("=== INSERINDO CHAVES NA TABELA HASH ===\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de chaves: " + chaves.length + "\n");

        for (Integer chave : chaves) {
            int indice = chave % tabelaHash.capacity();

            tabelaHash.inserirSemColisao(chave);

            System.out.println("Chave inserida: " + chave);
            System.out.println("Posição na tabela: [" + indice + "]");
            System.out.println();
        }

        System.out.println("=== ESTATÍSTICAS DA TABELA ===");
        System.out.println(tabelaHash.stats());

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA COMPLETA ===");
        System.out.println(tabelaHash);

        //Procurando as chaves fornecidas na tabela
        Integer[] chavesCoringa = {24349786, 74349586, 87364443};

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          VERIFICANDO CHAVES DO CORINGA                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < chavesCoringa.length; i++) {
            int chave = chavesCoringa[i];
            System.out.println("Chave " + (i + 1) + ": " + chave);

            Integer encontrada = tabelaHash.buscarSemColisao(chave);

            if (encontrada != null) {
                int indice = chave % tabelaHash.capacity();
                System.out.println("✅ VÁLIDA - Encontrada na posição [" + indice + "]");
            } else {
                System.out.println("❌ INVÁLIDA - Não encontrada (chave falsa!)");
            }
            System.out.println();
        }
    }
}

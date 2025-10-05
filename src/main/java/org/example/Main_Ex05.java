package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;

public class Main_Ex05 {
    public static void main(String[] args) {

        intKeyExtractor<Integer> extrator = num -> num;
        CriarHash<Integer> tabelaHash = new CriarHash<>(extrator, 10);

        Integer[] armaduras = {
                245780,
                754425,
                338590,
                129857,
                285276,
                567589,
                659876,
                822678
        };

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMONSTRAÇÃO DE COLISÕES COM ENDEREÇAMENTO ABERTO     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de armaduras: " + armaduras.length + "\n");

        int totalFalhas = 0;

        for (Integer armadura : armaduras) {

            int indice = armadura % tabelaHash.capacity();

            System.out.println("🦾 Armadura: " + armadura);
            System.out.println("   Posição esperada (hash): [" + indice + "]");

            boolean temColisao = verificarColisao(tabelaHash, indice);

            if (temColisao) {
                System.out.println("   ⚠️ COLISÃO DETECTADA!");
                System.out.println("   A posição [" + indice + "] já está ocupada!!");
                System.out.println("   Será sobrescrito por: " + armadura);
                totalFalhas++;
            } else {
                System.out.println("   ✅ Inserido na posição [" + indice + "]");
                tabelaHash.inserirSemColisao(armadura);
            }
            System.out.println();
        }

        System.out.println("╔══════════════════════════════════════════════════════=╗");
        System.out.println("║               RESULTADO FINAL                         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        System.out.println("✅ Armaduras inseridas: " + tabelaHash.size());
        System.out.println("❌ Inserções que falharam: " + totalFalhas);

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);
    }

    private static int encontrarPosicao(CriarHash<Integer> hash, int numero) {
        String tableStr = hash.toString();
        String[] lines = tableStr.split("\n");

        for (String line : lines) {
            if (line.contains("key=" + numero)) {
                int start = line.indexOf("[") + 1;
                int end = line.indexOf("]");
                return Integer.parseInt(line.substring(start, end));
            }
        }
        return -1;
    }

    private static boolean verificarColisao(CriarHash<Integer> hash, int posicao) {
        String tableStr = hash.toString();
        String[] lines = tableStr.split("\n");

        for (String line : lines) {
            if (line.trim().startsWith("[" + posicao + "]")) {
                return true; // Posição ocupada = colisão!
            }
        }
        return false; // Posição vazia
    }
}

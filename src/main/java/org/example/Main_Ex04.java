package org.example;

import org.example.classes.CriarHash;
import org.example.hash.ASCIIHashFunction;
import org.example.hash.ASCIIKeyExtractor;

public class Main_Ex04 {
    public static void main(String[] args) {
        ASCIIKeyExtractor extrator = new ASCIIKeyExtractor();
        ASCIIHashFunction hashFunc = new ASCIIHashFunction();

        CriarHash<String> tabelaHash = new CriarHash<>(extrator, hashFunc, 7);

        String[] inimigos = {"Ares", "Cheetah", "Circe"};

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMONSTRAÇÃO DE COLISÕES COM ENDEREÇAMENTO ABERTO     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições\n");

        for (String inimigo : inimigos) {
            int somaASCII = calcularSomaASCII(inimigo);
            int indice = somaASCII % tabelaHash.capacity();

            System.out.println("🦹 Inimigo: " + inimigo);
            System.out.println("   Cálculo ASCII:");
            mostrarCalculoASCII(inimigo);
            System.out.println("   Soma ASCII: " + somaASCII);
            System.out.println("   Índice: " + somaASCII + " % " + tabelaHash.capacity() + " = " + indice);

            boolean temColisao = verificarColisao(tabelaHash, indice);

            if (temColisao) {
                System.out.println("   ⚠️ COLISÃO DETECTADA!");
                System.out.println("   A posição [" + indice + "] já está ocupada!");
                System.out.println("   Será sobrescrito por: " + inimigo);
            } else {
                System.out.println("   ✅ Inserido na posição [" + indice + "]");
            }
            tabelaHash.inserirSemColisao(inimigo);
            System.out.println("   ✅ Inserido na posição [" + indice + "]\n");
        }

        System.out.println("=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);
    }
    private static int calcularSomaASCII(String str) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += (int) str.charAt(i);
        }
        return soma;
    }

    private static void mostrarCalculoASCII(String str) {
        System.out.print("   ");
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int ascii = (int) c;
            System.out.print("'" + c + "'(" + ascii + ")");
            if (i < str.length() - 1) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }
    private static int encontrarPosicao(CriarHash<String> hash, int numero) {
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

    private static boolean verificarColisao(CriarHash<String> hash, int posicao) {
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
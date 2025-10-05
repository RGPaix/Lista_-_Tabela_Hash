package org.example;

import org.example.classes.CriarHash;
import org.example.hash.ASCIIHashFunction;
import org.example.hash.ASCIIKeyExtractor;

public class Main_Ex09 {
    public static void main(String[] args) {
        ASCIIKeyExtractor extrator = new ASCIIKeyExtractor();
        ASCIIHashFunction hashFunc = new ASCIIHashFunction();

        CriarHash<String> tabelaHash = new CriarHash<>(extrator, hashFunc, 11);

        String[] teorias = {"oraculo", "sombra", "codigo", "iluminatti", "agenda"};

        System.out.println("=== INSERINDO TEORIAS NA TABELA HASH ===\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de teorias: " + teorias.length + "\n");

        for (String teoria : teorias) {
            int somaASCII = calcularSomaASCII(teoria);
            int indice = somaASCII % tabelaHash.capacity();

            System.out.println("Teoria: " + teoria);
            System.out.println("   Cálculo ASCII:");
            mostrarCalculoASCII(teoria);
            System.out.println("   Soma ASCII: " + somaASCII);
            System.out.println("   Índice: " + somaASCII + " % " + tabelaHash.capacity() + " = " + indice);

            tabelaHash.inserirSemColisao(teoria);
            System.out.println();
        }

        System.out.println("=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);

        System.out.println("\n=== ESTATÍSTICAS DA TABELA ===");
        System.out.println("\n" + tabelaHash.stats());
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
}
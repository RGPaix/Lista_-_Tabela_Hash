package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;
import org.example.models.Visitante;

public class Main_Ex03 {
    public static void main(String[] args) {
        intKeyExtractor<Visitante> extrator = Visitante::getNumero;

        CriarHash<Visitante> tabelaHash = new CriarHash<>(extrator, 11);

        Visitante[] visitantes = {
                new Visitante(1, "Betty"),
                new Visitante(2, "James"),
                new Visitante(3, "August"),
                new Visitante(4, "Stephen"),
                new Visitante(5, "Carolina"),
                new Visitante(6, "John"),
                new Visitante(7, "Sam"),
                new Visitante(8, "Chloe"),
                new Visitante(9, "Marcus"),
                new Visitante(10, "Ronan"),
                new Visitante(11, "Clara"),
                new Visitante(12, "Gracie"),
                new Visitante(13, "Taylor"),
                new Visitante(14, "Sophia"),
                new Visitante(15, "Elizabeth"),
                new Visitante(16, "Inez"),
                new Visitante(17, "Angelina"),
                new Visitante(18, "Cassandra"),
                new Visitante(19,"Dorothea"),
                new Visitante(20, "Kitty"),
        };

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMONSTRAÇÃO DE COLISÕES COM ENDEREÇAMENTO ABERTO     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de visitantes: " + visitantes.length + "\n");

        int totalColisoes = 0;

        for (int i = 0; i < visitantes.length; i++) {
            Visitante visitante = visitantes[i];
            int posicaoEsperada = visitante.getNumero() % tabelaHash.capacity();

            System.out.println("🔧 Inserindo: " + visitante);
            System.out.println("   Posição esperada (hash): [" + posicaoEsperada + "]");

            // Verificar SE JÁ EXISTE algo nesta posição
            boolean temColisao = verificarColisao(tabelaHash, posicaoEsperada);

            if (temColisao) {
                System.out.println("⚠️ COLISÃO DETECTADA!");
                System.out.println("   A posição [" + posicaoEsperada + "] já está ocupada");
                totalColisoes++;
            }

            tabelaHash.inserirSemColisao(visitante);
            System.out.println();
        }

        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║               RESULTADO FINAL                       ║");
        System.out.println("╚═════════════════════════════════════════════════════╝\n");

        System.out.println("⚡ Total de colisões: " + totalColisoes);
        System.out.println("✅ Visitantes inseridos: " + tabelaHash.size());
        System.out.println("❌ Visitantes perdidos: " + (visitantes.length - tabelaHash.size()));

        System.out.println("\n" + tabelaHash.stats());

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);
    }

    // Métodos auxiliares
    private static int encontrarPosicao(CriarHash<Visitante> hash, int numero) {
        // Busca linear na tabela para encontrar a posição real
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

    private static boolean verificarColisao(CriarHash<Visitante> hash, int posicao) {
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

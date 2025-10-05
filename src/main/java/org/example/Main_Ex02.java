package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;
import org.example.models.Codigo;

public class Main_Ex02 {
    public static void main(String[] args) {
        intKeyExtractor<Codigo> extrator = Codigo::getCodigo;

        CriarHash<Codigo> tabelaHash = new CriarHash<>(extrator, 10);

        Codigo[] codigos = {
                new Codigo(7, "Robin"),
                new Codigo(12, "Mulher Maravilha"),
                new Codigo(22, "Aquaman"),
                new Codigo(23, "Super-Homem"),
        };

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMONSTRAÇÃO DE COLISÕES COM ENDEREÇAMENTO ABERTO     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições\n");

        for (int i = 0; i < codigos.length; i++) {
            Codigo codigo = codigos[i];
            int posicaoEsperada = codigo.getCodigo() % tabelaHash.capacity();

            System.out.println("🔧 Inserindo: " + codigo);
            System.out.println("   Posição esperada (hash): [" + posicaoEsperada + "]");

            // Verificar SE JÁ EXISTE algo nesta posição
            boolean temColisao = verificarColisao(tabelaHash, posicaoEsperada);

            if (temColisao) {
                System.out.println("⚠️ COLISÃO DETECTADA!");
                System.out.println("   A posição [" + posicaoEsperada + "] já está ocupada");
            }

            tabelaHash.inserirSemColisao(codigo);
            System.out.println();
        }

        System.out.println("=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);

        System.out.println("\n" + tabelaHash.stats());

        // Testar buscas
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("🔍 TESTANDO BUSCAS:\n");

        for (Codigo codigo : codigos) {
            Codigo encontrada = tabelaHash.buscarEnderecoAberto(codigo.getCodigo());
            System.out.println("   Buscar peça " + codigo.getCodigo() + ": " +
                    (encontrada != null ? "✅ Encontrada" : "❌ Não encontrada"));
        }
    }

    // Métodos auxiliares
    private static int encontrarPosicao(CriarHash<Codigo> hash, int numero) {
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

    private static boolean verificarColisao(CriarHash<Codigo> hash, int posicao) {
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
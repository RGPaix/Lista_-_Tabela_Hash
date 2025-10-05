package org.example;

import org.example.classes.CriarHash;
import org.example.hash.intKeyExtractor;
import org.example.models.Cidade;

public class Main_Ex07 {
    public static void main(String[] args) {
        intKeyExtractor<Cidade> extrator = Cidade::getCodigo;
        CriarHash<Cidade> tabelaHash = new CriarHash<>(extrator, 5);

        Cidade[] cidades = {
            new Cidade(212, "Nova York"),
            new Cidade(213, "Los Angeles"),
            new Cidade(312, "Chicago"),
            new Cidade(305, "Miami"),
            new Cidade(214, "Dallas"),
            new Cidade(612, "Mineápolis"),
            new Cidade(404, "Atlanta"),
        };

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  DEMONSTRAÇÃO DE COLISÕES COM ENDEREÇAMENTO ABERTO     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Capacidade da tabela: " + tabelaHash.capacity() + " posições");
        System.out.println("📋 Total de cidades: " + cidades.length + "\n");

        int totalColisoes = 0;

        for (int i = 0; i < cidades.length; i++) {
        Cidade cidade = cidades[i];
        int posicaoEsperada = cidade.getCodigo() % tabelaHash.capacity();

        System.out.println("🔧 Inserindo: " + cidade);
        System.out.println("   Posição esperada (hash): [" + posicaoEsperada + "]");

        // Verificar SE JÁ EXISTE algo nesta posição
        boolean temColisao = verificarColisao(tabelaHash, posicaoEsperada);

        if (temColisao) {
            System.out.println("⚠️ COLISÃO DETECTADA!");
            System.out.println("   A posição [" + posicaoEsperada + "] já está ocupada");
            totalColisoes++;
        }

        tabelaHash.inserirSemColisao(cidade);
        System.out.println();
    }

        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║               RESULTADO FINAL                       ║");
        System.out.println("╚═════════════════════════════════════════════════════╝\n");

        System.out.println("⚡ Total de colisões: " + totalColisoes);
        System.out.println("✅ Cidades inseridas: " + tabelaHash.size());
        System.out.println("❌ Cidades perdidas: " + (cidades.length - tabelaHash.size()));

        System.out.println("\n" + tabelaHash.stats());

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA ===");
        System.out.println(tabelaHash);
    }

    private static boolean verificarColisao(CriarHash<Cidade> hash, int posicao) {
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
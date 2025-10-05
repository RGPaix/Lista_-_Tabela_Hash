package org.example;

import org.example.hash.intKeyExtractor;
import org.example.models.Peca;
import org.example.classes.CriarHash;

public class Main_Ex01 {
    public static void main(String[] args) {
        // Criar o extrator de chave (usa o número da peça como chave)
        intKeyExtractor<Peca> extrator = Peca::getNumero;

        // Criar a tabela hash com capacidade inicial de 11
        CriarHash<Peca> tabelaHash = new CriarHash<>(extrator, 11);

        // Criar 5 peças
        Peca[] pecas = {
                new Peca(101, 2.5),
                new Peca(205, 5.8),
                new Peca(312, 1.2),
                new Peca(428, 3.7),
                new Peca(537, 4.1)
        };

        System.out.println("=== INSERINDO PEÇAS NA TABELA HASH ===\n");

        // Inserir cada peça e mostrar a posição
        for (Peca peca : pecas) {
            tabelaHash.inserirSemColisao(peca);
            int posicao = calcularPosicao(peca.getNumero(), tabelaHash.capacity());

            System.out.println("Peça inserida: " + peca);
            System.out.println("Posição na tabela: [" + posicao + "]");
            System.out.println("Cálculo: " + peca.getNumero() + " % " +
                    tabelaHash.capacity() + " = " + posicao);
            System.out.println();
        }

        System.out.println("=== ESTATÍSTICAS DA TABELA ===");
        System.out.println(tabelaHash.stats());

        System.out.println("\n=== VISUALIZAÇÃO DA TABELA COMPLETA ===");
        System.out.println(tabelaHash);

        // Exemplo de busca
        System.out.println("\n=== TESTANDO BUSCA ===");
        int numeroBusca = 312;
        Peca encontrada = tabelaHash.buscarSemColisao(numeroBusca);
        System.out.println("Buscando peça número " + numeroBusca + ": " + encontrada);
    }

    private static int calcularPosicao(int chave, int capacidade) {
        return Math.abs(chave) % capacidade;
    }
}
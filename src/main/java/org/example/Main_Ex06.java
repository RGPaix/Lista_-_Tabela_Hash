package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main_Ex06 {
    static class HashComEncadeamento {
        private List<Integer>[] tabela;
        private int tamanho;

        @SuppressWarnings("unchecked")
        public HashComEncadeamento(int tamanho) {
            this.tamanho = tamanho;
            this.tabela = new ArrayList[tamanho];

            // Inicializar cada posição com uma lista vazia
            for (int i = 0; i < tamanho; i++) {
                tabela[i] = new ArrayList<>();
            }
        }

        public void inserir(int codigo) {
            int indice = codigo % tamanho;
            tabela[indice].add(codigo);
        }

        public void mostrarTabela() {
            for (int i = 0; i < tamanho; i++) {
                System.out.print("[" + i + "] → ");
                if (tabela[i].isEmpty()) {
                    System.out.println("vazia");
                } else {
                    for (int j = 0; j < tabela[i].size(); j++) {
                        System.out.print(tabela[i].get(j));
                        if (j < tabela[i].size() - 1) {
                            System.out.print(" → ");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        HashComEncadeamento tabela = new HashComEncadeamento(5);

        // Criar 5 flechas
        Integer[] flechas = {
                317,
                411,
                326,
                245,
                342
        };

        System.out.println("Capacidade da tabela: 5 posições\n");

        // Inserir cada flecha e mostrar a posição
        for (Integer flecha : flechas) {
            int indice = flecha % 5;
            System.out.println("Inserindo flecha " + flecha + " → posição [" + indice + "]");
            tabela.inserir(flecha);
        }

        System.out.println("\n=== CONTEÚDO DE CADA LISTA ===\n");
        tabela.mostrarTabela();
    }
}

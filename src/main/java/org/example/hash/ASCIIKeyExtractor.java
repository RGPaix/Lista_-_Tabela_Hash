package org.example.hash;

public class ASCIIKeyExtractor implements intKeyExtractor<String> {
    @Override
    public int getKey(String str) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += (int) str.charAt(i); // Soma o código ASCII de cada letra
        }
        return soma;
    }
}

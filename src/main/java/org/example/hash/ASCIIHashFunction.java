package org.example.hash;

public class ASCIIHashFunction implements HashFunction {
    @Override
    public int hash(int key, int tableSize) {
        // Se já receber a soma ASCII, só retorna o módulo
        return Math.abs(key) % tableSize;
    }
}

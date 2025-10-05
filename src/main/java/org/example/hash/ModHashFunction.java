package org.example.hash;

public class ModHashFunction implements HashFunction {
    @Override

    public int hash (int key, int capacity){
        int h = key % capacity;
        return (h < 0) ? h + capacity : h; //if ternário
    }
}

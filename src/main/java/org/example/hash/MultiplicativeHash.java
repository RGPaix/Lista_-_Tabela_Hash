package org.example.hash;

public class MultiplicativeHash implements HashFunction{
    private static final double A = 0.7188339887;

    @Override
    public int hash(int key, int capacity) {
        long k = Math.abs((long) key);
        double x = k * A;
        double frac = x - Math.floor(x);
        int h = (int)Math.floor(capacity * frac);

        return (h < 0) ? (h % capacity + capacity) % capacity : h;
    }
}

package org.example.hash;

@FunctionalInterface
public interface intKeyExtractor<T> {
    int getKey (T value);
}

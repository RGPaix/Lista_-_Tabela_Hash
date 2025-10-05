package org.example.interfaces;

public interface Hash<T> {
    void inserir(T[] h, T info, int id);
    T buscar(T[] h, int id);
    void inserirEnderecoAberto(T[] h, T info, int id);
    T buscarEnderecoAberto(T[] h, int id);
}

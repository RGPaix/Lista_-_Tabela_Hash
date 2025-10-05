package org.example.classes;

import org.example.hash.*;
import org.example.interfaces.Hash;

import java.util.Objects;

public class CriarHash<T> implements Hash<T> {
    private static final double MAX_LOAD = 0.7;
    private HashTableEntry<T>[] table;
    private int size = 0;

    private final intKeyExtractor<T> keyExtractor;
    private final HashFunction hashFunction;

    @SuppressWarnings("unchecked")
    public CriarHash(intKeyExtractor<T> keyExtractor, HashFunction hashFunction, int capacity) {
        this.keyExtractor = Objects.requireNonNull(keyExtractor);
        this.hashFunction = (hashFunction != null) ? hashFunction : new ModHashFunction();
        if (capacity < 4) capacity = 4;
        this.table = (HashTableEntry<T>[]) new HashTableEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new HashTableEntry<>();
        }
    }

    public CriarHash(intKeyExtractor<T> keyExtractor, int capacity) {
        this(keyExtractor, new ModHashFunction(), capacity);
    }

    public CriarHash(intKeyExtractor<T> keyExtractor) {
        this(keyExtractor, 16);
    }

    // Métodos da interface Hash
    @Override
    public void inserir(T[] h, T info, int id) {
        inserirSemColisao(info);
    }

    @Override
    public T buscar(T[] h, int id) {
        return buscarSemColisao(id);
    }

    @Override
    public void inserirEnderecoAberto(T[] h, T info, int id) {
        inserirEnderecoAberto(info);
    }

    @Override
    public T buscarEnderecoAberto(T[] h, int id) {
        return buscarEnderecoAberto(id);
    }

    // Métodos de inserção e busca sem colisão
    public void inserirSemColisao(T info) {
        int id = keyExtractor.getKey(info);
        //ensureCapacity();
        int index = baseIndex(id);
        if (table[index].state != State.OCCUPIED) size++;
        table[index].key = id;
        table[index].value = info;
        table[index].state = State.OCCUPIED;
    }

    public T buscarSemColisao(int id) {
        int chave = baseIndex(id);

        if (table[chave].state == State.OCCUPIED && table[chave].key == id) {
            return table[chave].value;
        }
        return null;
    }

    public T buscarSemColisao(T value) {
        return buscarSemColisao(keyExtractor.getKey(value));
    }

    // Métodos de endereçamento aberto
    public void inserirEnderecoAberto(T info) {
        int id = keyExtractor.getKey(info);
        ensureCapacity();
        int index = findSlotForInsert(id);
        if (index < 0) {
            rehash(table.length * 2);
            index = findSlotForInsert(id);
        }
        if (table[index].state != State.OCCUPIED) {
            size++;
        }
        table[index].key = id;
        table[index].value = info;
        table[index].state = State.OCCUPIED;
    }

    public T buscarEnderecoAberto(int id) {
        int index = findSlotForSearch(id);
        if (index >= 0 && table[index].state == State.OCCUPIED) {
            return table[index].value;
        }
        return null;
    }

    public T buscarEnderecoAberto(T value) {
        return buscarEnderecoAberto(keyExtractor.getKey(value));
    }

    public T removeEnderecoAberto(int id) {
        int index = findSlotForSearch(id);
        T old = null;
        if (index >= 0 && table[index].state == State.OCCUPIED) {
            old = table[index].value;
            table[index].value = null;
            table[index].state = State.DELETED;
            size--;
        }
        return old;
    }

    // Métodos auxiliares
    private int findSlotForInsert(int id) {
        int base = baseIndex(id);
        int i = 0;
        int firstDeleted = -1;

        while (i < table.length) {
            int index = (base + i) % table.length;

            if (table[index].state == State.EMPTY) {
                return (firstDeleted >= 0) ? firstDeleted : index;
            }

            if (table[index].state == State.DELETED && firstDeleted < 0) {
                firstDeleted = index;
            }

            if (table[index].state == State.OCCUPIED && table[index].key == id) {
                return index;
            }

            i++;
        }

        return (firstDeleted >= 0) ? firstDeleted : -1;
    }

    private int findSlotForSearch(int id) {
        int base = baseIndex(id);
        int i = 0;

        while (i < table.length) {
            int index = (base + i) % table.length;

            if (table[index].state == State.EMPTY) {
                return -1;
            }

            if (table[index].state == State.OCCUPIED && table[index].key == id) {
                return index;
            }

            i++;
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    private void rehash(int newCapacity) {
        HashTableEntry<T>[] oldTable = table;
        table = (HashTableEntry<T>[]) new HashTableEntry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            table[i] = new HashTableEntry<>();
        }

        size = 0;

        for (HashTableEntry<T> entry : oldTable) {
            if (entry.state == State.OCCUPIED) {
                inserirEnderecoAberto(entry.value);
            }
        }
    }

    private int baseIndex(int id) {
        return hashFunction.hash(id, capacity());
    }

    private void ensureCapacity() {
        if (loadFactor() > MAX_LOAD) {
            rehash(capacity() * 2);
        }
    }

    // Métodos utilitários
    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

    public double loadFactor() {
        return (double) size / capacity();
    }

    public String stats() {
        return "Hash {\n" +
                "\ttamanho = " + size + "\n" +
                "\tcapacidade = " + capacity() + "\n" +
                "\tocupação = " + String.format("%.2f", loadFactor()) + "\n" +
                "}";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CriarHash[\n");
        for (int i = 0; i < table.length; i++) {
            if (table[i].state == State.OCCUPIED) {
                sb.append("  [").append(i).append("] key=").append(table[i].key)
                        .append(" value=").append(table[i].value).append("\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

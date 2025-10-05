package org.example.models;

public class Peca {
    private int numero;
    private double peso;

    public Peca(int numero, double peso) {
        this.numero = numero;
        this.peso = peso;
    }

    public int getNumero() {
        return numero;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Peca{numero=" + numero + ", peso=" + peso + "kg}";
    }
}
package org.example.models;

public class Codigo {
    private int codigo;
    private String nome;

    public Codigo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Código{numero=" + codigo + ", nome =" + nome + "}";
    }
}

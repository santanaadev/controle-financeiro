package model;

import java.text.Normalizer;
import java.util.Locale;

public class Gasto {
    private double valor;
    private String categoria;

    public Gasto(double valor, String categoria) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do gasto deve ser maior que zero");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria do gasto não pode ser vazia");
        }
        this.valor = valor;
        this.categoria = normalize(categoria);
    }

    public void listarGasto () {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Categoria: " + categoria + System.lineSeparator() + "Valor: R$ " + String.format("%.2f", valor);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do gasto deve ser maior que zero");
        }
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("A categoria do gasto não pode ser vazia");
        }
        this.categoria = normalize(categoria);
    }

    // Normaliza uma string removendo acentos e convertendo para minúsculas
    private static String normalize(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFD);
        n = n.replaceAll("\\p{M}", "");
        return n.toLowerCase(Locale.ROOT).trim();
    }
}
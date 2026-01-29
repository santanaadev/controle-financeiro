package model;

public class Gasto {
    private double valor;
    private String categoria;

    public Gasto(double valor, String categoria) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do gasto deve ser maior que zero");
        }
        this.valor = valor;
        this.categoria = categoria;
    }

    public void listarGasto () {
        System.out.println("Categoria: " + categoria);
        System.out.println("Valor: R$ " + valor);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
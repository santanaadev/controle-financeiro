package repository;

import model.Gasto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GastoRepository {
    public void salvarGastos(List<Gasto> gastos) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gastos.txt"))) {
            for (Gasto gasto : gastos) {
                writer.write(gasto.getCategoria() + ";" + gasto.getValor());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar gastos: " + e.getMessage());
        }
    }
    public List<Gasto> carregarGastos() {
        return null;
    }
}

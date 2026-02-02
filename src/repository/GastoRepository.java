package repository;

import model.Gasto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class GastoRepository {

    private static final Path PATH = Paths.get("gasto.txt");

    public void salvarGastos(List<Gasto> gastos) {
        try (BufferedWriter writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Gasto gasto : gastos) {
                writer.write(gasto.getCategoria() + ";" + gasto.getValor());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar gastos: " + e.getMessage());
        }
    }

    public List<Gasto> carregarGastos() {
        List<Gasto> gastos = new ArrayList<>();
        if (!Files.exists(PATH)) {
            return gastos;
        }

        try (BufferedReader reader = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length != 2) continue;
                String categoria = partes[0].trim();
                try {
                    double valor = Double.parseDouble(partes[1].trim());
                    try {
                        gastos.add(new Gasto(valor, categoria));
                    } catch (IllegalArgumentException ignored) {

                    }
                } catch (NumberFormatException ignored) {

                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar gastos: " + e.getMessage());
        }

        return gastos;
    }
}

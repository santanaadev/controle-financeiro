package service;

import model.Gasto;
import repository.GastoRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GastoService {

     private final List<Gasto> gastos;
     private final GastoRepository repository;

     public GastoService() {
         this.repository = new GastoRepository();
         this.gastos = Collections.synchronizedList(new ArrayList<>());
     }

     public void carregarGastos() {
         List<Gasto> carregados = repository.carregarGastos();
         synchronized (gastos) {
             gastos.clear();
             gastos.addAll(carregados);
         }
     }

     public void salvarGastos() {
         repository.salvarGastos(new ArrayList<>(gastos));
     }

     public void adicionarGasto (Gasto gasto) {
         gastos.add(gasto);
     }

     public void listarTodosOsGastos() {
         synchronized (gastos) {
             for (Gasto gasto : gastos) {
                 gasto.listarGasto();
             }
         }
     }

    public double calcularTotal() {
        double total = 0;
        synchronized (gastos) {
            for (Gasto gasto : gastos) {
                total += gasto.getValor();
            }
        }

        return total;
    }

    // Normaliza uma string removendo acentos e convertendo para minúsculas
    private static String normalize(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFD);
        // remove marcas de acento (diacríticos)
        n = n.replaceAll("\\p{M}", "");
        return n.toLowerCase(Locale.ROOT).trim();
    }

    public double calcularTotalPorCategoria(String categoria) {
        double total = 0;
        String target = normalize(categoria);
        synchronized (gastos) {
            for (Gasto gasto : gastos) {
                if (normalize(gasto.getCategoria()).equals(target)) {
                    total += gasto.getValor();
                }
            }
        }
        return total;
    }

    public List<Gasto> getGastos() {
        synchronized (gastos) {
            return Collections.unmodifiableList(new ArrayList<>(gastos));


        }
    }
}

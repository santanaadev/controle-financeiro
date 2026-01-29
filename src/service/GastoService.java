package service;

import model.Gasto;
import repository.GastoRepository;

import java.util.ArrayList;
import java.util.List;

public class GastoService {

     private List<Gasto> gastos = new ArrayList<>();
     private GastoRepository repository;

     public GastoService() {
         this.repository = new GastoRepository();
         this.gastos = new ArrayList<>();
     }

     public void carregarGastos() {
         this.gastos = repository.carregarGastos();
     }

     public void salvarGastos() {
         repository.salvarGastos(gastos);
     }

     public void adicionarGasto (Gasto gasto) {
         gastos.add(gasto);
     }

     public void listarTodosOsGastos() {
         for (Gasto gasto: gastos) {
             gasto.listarGasto();
         }
     }

    public double calcularTotal() {
        double total = 0;

        for (Gasto gasto: gastos ) {
            total += gasto.getValor();
        }

        return total;
    }

    public double calcularTotalPorCategoria(String categoria) {
         double total = 0;

         for (Gasto gasto : gastos) {
             if (gasto.getCategoria().equalsIgnoreCase(categoria)) {
                 total += gasto.getValor();
             }
         }
         return total;
    }
}

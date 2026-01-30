package app;

import model.Gasto;
import service.GastoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GastoService service = new GastoService();

        service.carregarGastos();

        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Adicionar gasto");
            System.out.println("2 - Listar gastos");
            System.out.println("3 - Total geral");
            System.out.println("4 - Total por categoria");
            System.out.println("0 - Salvar e sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("Categoria: ");
                    String categoria = scanner.nextLine();

                    System.out.println("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        Gasto gasto = new Gasto(valor, categoria);
                        service.adicionarGasto(gasto);
                        System.out.println("✅ Gasto adicionado!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n=== GASTOS CADASTRADOS ===");
                    service.listarTodosOsGastos();
                    break;

                case 3:
                    System.out.println("Total geral: R$ " + service.calcularTotal());
                    break;

                case 4:
                    System.out.println("Digite a categoria: ");
                    String categoriaBusca = scanner.nextLine();

                    System.out.println("Total em " + categoriaBusca + ":R$"
                            + service.calcularTotalPorCategoria(categoriaBusca));
                    break;

                case 0:
                    service.salvarGastos();
                    System.out.println("💾 Gastos salvos. Encerrando...");
                    break;

                default:
                    System.out.println("❌ Opção inválida");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

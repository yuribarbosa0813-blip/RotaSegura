package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.Locacao;

public class HistoricoLocacao {

    private static final String ARQUIVO =
            "historico_locacoes.txt";

    public void salvar(Locacao locacao)
            throws IOException {

        try (FileWriter fileWriter =
                     new FileWriter(ARQUIVO, true);
             PrintWriter writer =
                     new PrintWriter(fileWriter)) {

            writer.println("=================================");
            writer.println("       HISTÓRICO DE LOCAÇÃO");
            writer.println("=================================");
            writer.println();

            writer.println(
                    "Cliente: "
                    + locacao.getCliente().getNome()
            );

            writer.println(
                    "CPF: "
                    + locacao.getCliente().getCpf()
            );

            writer.println(
                    "Veículo: "
                    + locacao.getVeiculo().getModelo()
            );

            writer.println(
                    "Categoria: "
                    + locacao.getVeiculo().getCategoria()
            );

            writer.println(
                    "Placa: "
                    + locacao.getVeiculo().getPlaca()
            );

            writer.println(
                    "Data de retirada: "
                    + locacao.getDataInicio()
            );

            writer.println(
                    "Data de devolução: "
                    + locacao.getDataFim()
            );

            writer.println(
                    "Dias: "
                    + locacao.getDias()
            );

            writer.println(
                    "Valor da diária: R$ "
                    + String.format(
                            "%.2f",
                            locacao.getVeiculo()
                                    .calcularDiaria()
                    )
            );

            writer.println(
                    "Seguro: R$ "
                    + String.format(
                            "%.2f",
                            locacao.getVeiculo()
                                    .calcularSeguro(
                                            locacao.getDias()
                                    )
                    )
            );

            writer.println(
                    "Manutenção: R$ "
                    + String.format(
                            "%.2f",
                            locacao.getVeiculo()
                                    .calcularManutencao()
                    )
            );

            writer.println(
                    "Valor total: R$ "
                    + String.format(
                            "%.2f",
                            locacao.getValorTotal()
                    )
            );

            writer.println(
                    "Status: "
                    + (locacao.isAtiva()
                            ? "Ativa"
                            : "Finalizada")
            );

            writer.println();
            writer.println("=================================");
            writer.println();
        }
    }
}
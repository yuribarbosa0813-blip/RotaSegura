package contrato;

import modelo.Locacao;

public class RelatorioFechamento implements GeradorDocumento {

    private final Locacao locacao;

    public RelatorioFechamento(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public String gerarDocumento() {

        double valorDiarias =
                locacao.getVeiculo().calcularDiaria()
                * locacao.getDias();

        double valorSeguro =
                locacao.getVeiculo().calcularSeguro(
                        locacao.getDias()
                );

        double valorManutencao =
                locacao.getVeiculo().calcularManutencao();

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("=================================\n");
        relatorio.append("       RELATÓRIO DE FECHAMENTO\n");
        relatorio.append("=================================\n\n");

        relatorio.append("CLIENTE\n");
        relatorio.append("---------------------------------\n");

        relatorio.append("Nome: ")
                .append(locacao.getCliente().getNome())
                .append("\n");

        relatorio.append("CPF: ")
                .append(locacao.getCliente().getCpf())
                .append("\n\n");

        relatorio.append("VEÍCULO\n");
        relatorio.append("---------------------------------\n");

        relatorio.append("Modelo: ")
                .append(locacao.getVeiculo().getModelo())
                .append("\n");

        relatorio.append("Categoria: ")
                .append(locacao.getVeiculo().getCategoria())
                .append("\n");

        relatorio.append("Placa: ")
                .append(locacao.getVeiculo().getPlaca())
                .append("\n\n");

        relatorio.append("PERÍODO DA LOCAÇÃO\n");
        relatorio.append("---------------------------------\n");

        relatorio.append("Data de retirada: ")
                .append(locacao.getDataInicio())
                .append("\n");

        relatorio.append("Data de devolução: ")
                .append(locacao.getDataFim())
                .append("\n");

        relatorio.append("Dias: ")
                .append(locacao.getDias())
                .append("\n\n");

        relatorio.append("VALORES\n");
        relatorio.append("---------------------------------\n");

        relatorio.append("Diárias: R$ ")
                .append(String.format("%.2f", valorDiarias))
                .append("\n");

        relatorio.append("Seguro: R$ ")
                .append(String.format("%.2f", valorSeguro))
                .append("\n");

        relatorio.append("Manutenção: R$ ")
                .append(String.format("%.2f", valorManutencao))
                .append("\n");

        relatorio.append("VALOR TOTAL: R$ ")
                .append(String.format(
                        "%.2f",
                        locacao.getValorTotal()
                ))
                .append("\n\n");

        relatorio.append("Status: ")
                .append(locacao.isAtiva()
                        ? "Ativa"
                        : "Finalizada")
                .append("\n\n");

        relatorio.append("=================================\n");
        relatorio.append("          ROTA SEGURA\n");
        relatorio.append("=================================\n");

        return relatorio.toString();
    }

    @Override
    public void imprimirDocumento() {
        System.out.println(gerarDocumento());
    }
}
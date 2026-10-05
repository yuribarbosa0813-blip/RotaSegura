package contrato;

import modelo.Locacao;

public class ComprovanteLocacao implements GeradorDocumento {

    private final Locacao locacao;

    public ComprovanteLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public String gerarDocumento() {

        StringBuilder documento = new StringBuilder();

        documento.append("=================================\n");
        documento.append("       COMPROVANTE DE LOCAÇÃO\n");
        documento.append("=================================\n\n");

        documento.append("CLIENTE\n");
        documento.append("---------------------------------\n");

        documento.append("Nome: ")
                .append(locacao.getCliente().getNome())
                .append("\n");

        documento.append("CPF: ")
                .append(locacao.getCliente().getCpf())
                .append("\n\n");

        documento.append("VEÍCULO\n");
        documento.append("---------------------------------\n");

        documento.append("Modelo: ")
                .append(locacao.getVeiculo().getModelo())
                .append("\n");

        documento.append("Categoria: ")
                .append(locacao.getVeiculo().getCategoria())
                .append("\n");

        documento.append("Placa: ")
                .append(locacao.getVeiculo().getPlaca())
                .append("\n\n");

        documento.append("LOCAÇÃO\n");
        documento.append("---------------------------------\n");

        documento.append("Data de retirada: ")
                .append(locacao.getDataInicio())
                .append("\n");

        documento.append("Data de devolução: ")
                .append(locacao.getDataFim())
                .append("\n");

        documento.append("Dias: ")
                .append(locacao.getDias())
                .append("\n\n");

        documento.append("VALORES\n");
        documento.append("---------------------------------\n");

        documento.append("Diária: R$ ")
                .append(String.format(
                        "%.2f",
                        locacao.getVeiculo().calcularDiaria()
                ))
                .append("\n");

        documento.append("Seguro: R$ ")
                .append(String.format(
                        "%.2f",
                        locacao.getVeiculo()
                                .calcularSeguro(locacao.getDias())
                ))
                .append("\n");

        documento.append("Manutenção: R$ ")
                .append(String.format(
                        "%.2f",
                        locacao.getVeiculo().calcularManutencao()
                ))
                .append("\n");

        documento.append("Valor total: R$ ")
                .append(String.format(
                        "%.2f",
                        locacao.getValorTotal()
                ))
                .append("\n\n");

        documento.append("Status: ")
                .append(locacao.isAtiva()
                        ? "Ativa"
                        : "Finalizada")
                .append("\n\n");

        documento.append("=================================\n");
        documento.append("          ROTA SEGURA\n");
        documento.append("=================================\n");

        return documento.toString();
    }

    @Override
    public void imprimirDocumento() {
        System.out.println(gerarDocumento());
    }
}
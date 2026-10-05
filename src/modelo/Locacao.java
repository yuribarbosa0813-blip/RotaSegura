package modelo;

import java.time.LocalDate;

import excecao.DadoInvalidoException;

public class Locacao {

    private final Cliente cliente;
    private final Veiculo veiculo;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final int dias;
    private double valorTotal;
    private boolean ativa;

    public Locacao(
            Cliente cliente,
            Veiculo veiculo,
            LocalDate dataInicio,
            LocalDate dataFim)
            throws DadoInvalidoException {

        if (cliente == null) {
            throw new DadoInvalidoException("Cliente inválido.");
        }

        if (veiculo == null) {
            throw new DadoInvalidoException("Veículo inválido.");
        }

        if (dataInicio == null || dataFim == null) {
            throw new DadoInvalidoException(
                    "As datas da locação são obrigatórias."
            );
        }

        if (dataFim.isBefore(dataInicio)) {
            throw new DadoInvalidoException(
                    "A data de devolução não pode ser anterior à data de retirada."
            );
        }

        if (dataInicio.isBefore(LocalDate.now())) {
            throw new DadoInvalidoException(
                    "A data de retirada não pode ser anterior à data atual."
            );
        }

        if (!veiculo.isDisponivel()) {
            throw new DadoInvalidoException(
                    "Veículo não está disponível."
            );
        }

        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;

        this.dias = (int) (
                java.time.temporal.ChronoUnit.DAYS.between(
                        dataInicio,
                        dataFim
                )
        ) + 1;

        if (dias <= 0) {
            throw new DadoInvalidoException(
                    "Quantidade de dias inválida."
            );
        }

        this.valorTotal = calcularValorTotal();
        this.ativa = true;

        veiculo.marcarComoAlugado();
    }

    private double calcularValorTotal() {

        double diaria = veiculo.calcularDiaria();
        double seguro = veiculo.calcularSeguro(dias);
        double manutencao = veiculo.calcularManutencao();

        return (diaria * dias) + seguro + manutencao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public int getDias() {
        return dias;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void finalizar() throws DadoInvalidoException {

        if (!ativa) {
            throw new DadoInvalidoException(
                    "Locação já está finalizada."
            );
        }

        ativa = false;
        veiculo.marcarComoDisponivel();
    }
}
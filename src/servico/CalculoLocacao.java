package servico;

import modelo.Locacao;

public class CalculoLocacao {

    public double calcularValorDiarias(Locacao locacao) {
        return locacao.getVeiculo().calcularDiaria()
                * locacao.getDias();
    }

    public double calcularSeguro(Locacao locacao) {
        return locacao.getVeiculo().calcularSeguro(
                locacao.getDias());
    }

    public double calcularManutencao(Locacao locacao) {
        return locacao.getVeiculo().calcularManutencao();
    }

    public double calcularValorTotal(Locacao locacao) {
        return locacao.getValorTotal();
    }
}
package modelo;

import excecao.DadoInvalidoException;

public class Popular extends Veiculo {

    public Popular(String placa, String modelo, int ano, double quilometragem)
            throws DadoInvalidoException {

        super(placa, modelo, ano, quilometragem);
    }

    @Override
    public double calcularDiaria() {
        return 100.0;
    }

    @Override
    public double calcularSeguro(int dias) {
        return 20.0 * dias;
    }

    @Override
    public double calcularManutencao() {
        return 150.0;
    }

    @Override
    public String getCategoria() {
        return "Popular";
    }
}
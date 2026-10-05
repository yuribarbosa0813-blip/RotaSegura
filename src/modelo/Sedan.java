package modelo;

import excecao.DadoInvalidoException;

public class Sedan extends Veiculo {

    public Sedan(String placa, String modelo, int ano, double quilometragem)
            throws DadoInvalidoException {

        super(placa, modelo, ano, quilometragem);
    }

    @Override
    public double calcularDiaria() {
        return 180.0;
    }

    @Override
    public double calcularSeguro(int dias) {
        return 30.0 * dias;
    }

    @Override
    public double calcularManutencao() {
        return 250.0;
    }

    @Override
    public String getCategoria() {
        return "Sedan";
    }
}
package modelo;

import excecao.DadoInvalidoException;

public class Suv extends Veiculo {

    public Suv(String placa, String modelo, int ano, double quilometragem)
            throws DadoInvalidoException {

        super(placa, modelo, ano, quilometragem);
    }

    @Override
    public double calcularDiaria() {
        return 280.0;
    }

    @Override
    public double calcularSeguro(int dias) {
        return 40.0 * dias;
    }

    @Override
    public double calcularManutencao() {
        return 350.0;
    }

    @Override
    public String getCategoria() {
        return "SUV";
    }
}
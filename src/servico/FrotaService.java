package servico;

import java.util.ArrayList;
import java.util.List;

import excecao.DadoInvalidoException;
import modelo.Veiculo;

public class FrotaService {

    private final List<Veiculo> veiculos;

    public FrotaService() {
        veiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo)
            throws DadoInvalidoException {

        if (veiculo == null) {
            throw new DadoInvalidoException(
                    "Veículo inválido."
            );
        }

        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new DadoInvalidoException(
                        "Já existe um veículo com essa placa."
                );
            }
        }

        veiculos.add(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }

    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.isDisponivel()) {
                disponiveis.add(veiculo);
            }
        }

        return disponiveis;
    }

    public Veiculo buscarPorPlaca(String placa)
            throws DadoInvalidoException {

        if (placa == null || placa.isBlank()) {
            throw new DadoInvalidoException(
                    "Placa inválida."
            );
        }

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }

        throw new DadoInvalidoException(
                "Veículo não encontrado."
        );
    }

    public int quantidadeVeiculos() {
        return veiculos.size();
    }
}
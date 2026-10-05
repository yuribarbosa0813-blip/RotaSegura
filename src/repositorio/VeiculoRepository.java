package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Veiculo;

public class VeiculoRepository {

    private final List<Veiculo> veiculos;

    public VeiculoRepository() {
        veiculos = new ArrayList<>();
    }

    public void salvar(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public List<Veiculo> buscarTodos() {
        return new ArrayList<>(veiculos);
    }

    public Veiculo buscarPorPlaca(String placa) {

        for (Veiculo veiculo : veiculos) {

            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }

        return null;
    }

    public void remover(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}
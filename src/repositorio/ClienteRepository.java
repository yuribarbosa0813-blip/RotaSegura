package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteRepository {

    private final List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
    }

    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorCpf(String cpf) {

        for (Cliente cliente : clientes) {

            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente;
            }
        }

        return null;
    }

    public void remover(Cliente cliente) {
        clientes.remove(cliente);
    }
}
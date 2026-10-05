	package servico;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import excecao.DadoInvalidoException;
	import modelo.Cliente;
	
	public class ClienteService {
	
	    private final List<Cliente> clientes;
	
	    public ClienteService() {
	        clientes = new ArrayList<>();
	    }
	
	    public void cadastrarCliente(Cliente cliente)
	            throws DadoInvalidoException {
	
	        if (cliente == null) {
	            throw new DadoInvalidoException(
	                    "Cliente inválido."
	            );
	        }
	
	        for (Cliente c : clientes) {
	            if (c.getCpf().equals(cliente.getCpf())) {
	                throw new DadoInvalidoException(
	                        "Já existe um cliente com esse CPF."
	                );
	            }
	        }
	
	        clientes.add(cliente);
	    }
	
	    public List<Cliente> listarClientes() {
	        return new ArrayList<>(clientes);
	    }
	
	    public Cliente buscarPorCpf(String cpf)
	            throws DadoInvalidoException {
	
	        if (cpf == null || cpf.isBlank()) {
	            throw new DadoInvalidoException(
	                    "CPF inválido."
	            );
	        }
	
	        for (Cliente cliente : clientes) {
	            if (cliente.getCpf().equals(cpf)) {
	                return cliente;
	            }
	        }
	
	        throw new DadoInvalidoException(
	                "Cliente não encontrado."
	        );
	    }
	
	    public int quantidadeClientes() {
	        return clientes.size();
	    }
	}
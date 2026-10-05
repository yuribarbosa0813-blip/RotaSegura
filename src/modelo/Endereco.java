package modelo;

import excecao.DadoInvalidoException;

public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String rua, String numero, String bairro,
                    String cidade, String estado, String cep)
            throws DadoInvalidoException {

        if (rua == null || rua.isBlank()) {
            throw new DadoInvalidoException("Rua inválida.");
        }

        if (numero == null || numero.isBlank()) {
            throw new DadoInvalidoException("Número inválido.");
        }

        if (bairro == null || bairro.isBlank()) {
            throw new DadoInvalidoException("Bairro inválido.");
        }

        if (cidade == null || cidade.isBlank()) {
            throw new DadoInvalidoException("Cidade inválida.");
        }

        if (estado == null || estado.isBlank()) {
            throw new DadoInvalidoException("Estado inválido.");
        }

        if (cep == null || cep.isBlank()) {
            throw new DadoInvalidoException("CEP inválido.");
        }

        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }
}
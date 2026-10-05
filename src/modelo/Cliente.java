package modelo;

import excecao.DadoInvalidoException;

public class Cliente {

    private final String cpf;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String cpf, String nome, String telefone, String email)
            throws DadoInvalidoException {

        if (cpf == null || cpf.isBlank()) {
            throw new DadoInvalidoException("CPF inválido.");
        }

        if (nome == null || nome.isBlank()) {
            throw new DadoInvalidoException("Nome inválido.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new DadoInvalidoException("Telefone inválido.");
        }

        if (email == null || email.isBlank()) {
            throw new DadoInvalidoException("E-mail inválido.");
        }

        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) throws DadoInvalidoException {
        if (nome == null || nome.isBlank()) {
            throw new DadoInvalidoException("Nome inválido.");
        }

        this.nome = nome;
    }

    public void setTelefone(String telefone) throws DadoInvalidoException {
        if (telefone == null || telefone.isBlank()) {
            throw new DadoInvalidoException("Telefone inválido.");
        }

        this.telefone = telefone;
    }

    public void setEmail(String email) throws DadoInvalidoException {
        if (email == null || email.isBlank()) {
            throw new DadoInvalidoException("E-mail inválido.");
        }

        this.email = email;
    }
}
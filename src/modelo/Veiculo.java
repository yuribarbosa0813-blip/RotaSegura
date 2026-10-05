package modelo;

import excecao.DadoInvalidoException;	

public abstract class Veiculo {

    private final String placa;
    private String modelo;
    private int ano;
    private double quilometragem;
    private boolean disponivel;



public Veiculo(String placa, String modelo, int ano, double quilometragem)
        throws DadoInvalidoException {

    if (placa == null || placa.isBlank()) {
        throw new DadoInvalidoException("Placa inválida.");
    }

    if (modelo == null || modelo.isBlank()) {
        throw new DadoInvalidoException("Modelo inválido.");
    }

    if (ano < 1990) {
        throw new DadoInvalidoException("Ano inválido.");
    }

    if (quilometragem < 0) {
        throw new DadoInvalidoException("Quilometragem inválida.");
    }

    this.placa = placa;
    this.modelo = modelo;
    this.ano = ano;
    this.quilometragem = quilometragem;
    this.disponivel = true;
}

public String getPlaca() {
    return placa;
}

public String getModelo() {
    return modelo;
}

public int getAno() {
    return ano;
}

public double getQuilometragem() {
    return quilometragem;
}

public boolean isDisponivel() {
    return disponivel;
}

public void setModelo(String modelo) throws DadoInvalidoException {
    if (modelo == null || modelo.isBlank()) {
        throw new DadoInvalidoException("Modelo inválido.");
    }

    this.modelo = modelo;
}

public void setAno(int ano) throws DadoInvalidoException {
    if (ano < 1990) {
        throw new DadoInvalidoException("Ano inválido.");
    }

    this.ano = ano;
}

public void setQuilometragem(double quilometragem)
        throws DadoInvalidoException {

    if (quilometragem < 0) {
        throw new DadoInvalidoException("Quilometragem inválida.");
    }

    this.quilometragem = quilometragem;
}

public void marcarComoAlugado() throws DadoInvalidoException {
    if (!disponivel) {
        throw new DadoInvalidoException("Veículo já está alugado.");
    }

    disponivel = false;
}

public void marcarComoDisponivel() {
    disponivel = true;
}
public abstract double calcularDiaria();

public abstract double calcularSeguro(int dias);

public abstract double calcularManutencao();

public abstract String getCategoria();

}
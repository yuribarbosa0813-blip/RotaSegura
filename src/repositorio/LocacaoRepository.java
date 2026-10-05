package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Locacao;

public class LocacaoRepository {

    private final List<Locacao> locacoes;

    public LocacaoRepository() {
        locacoes = new ArrayList<>();
    }

    public void salvar(Locacao locacao) {
        locacoes.add(locacao);
    }

    public List<Locacao> buscarTodos() {
        return new ArrayList<>(locacoes);
    }

    public List<Locacao> buscarAtivas() {

        List<Locacao> ativas = new ArrayList<>();

        for (Locacao locacao : locacoes) {

            if (locacao.isAtiva()) {
                ativas.add(locacao);
            }
        }

        return ativas;
    }

    public void remover(Locacao locacao) {
        locacoes.remove(locacao);
    }
}
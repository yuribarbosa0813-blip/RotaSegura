	package servico;
	
	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.List;
	
	import excecao.DadoInvalidoException;
	import modelo.Cliente;
	import modelo.Locacao;
	import modelo.Veiculo;
	
	public class LocacaoService {
	
	    private final List<Locacao> locacoes;
	
	    public LocacaoService() {
	        locacoes = new ArrayList<>();
	    }
	
	    public Locacao criarLocacao(
	            Cliente cliente,
	            Veiculo veiculo,
	            LocalDate dataInicio,
	            LocalDate dataFim)
	            throws DadoInvalidoException {
	
	        for (Locacao locacao : locacoes) {
	            if (locacao.getVeiculo() == veiculo
	                    && locacao.isAtiva()) {
	
	                throw new DadoInvalidoException(
	                        "Veículo já está ocupado em outra locação."
	                );
	            }
	        }
	
	        Locacao locacao = new Locacao(
	                cliente,
	                veiculo,
	                dataInicio,
	                dataFim
	        );
	
	        locacoes.add(locacao);
	
	        return locacao;
	    }
	
	    public void finalizarLocacao(Locacao locacao)
	            throws DadoInvalidoException {
	
	        if (locacao == null) {
	            throw new DadoInvalidoException(
	                    "Locação inválida."
	            );
	        }
	
	        if (!locacao.isAtiva()) {
	            throw new DadoInvalidoException(
	                    "A locação já foi finalizada."
	            );
	        }
	
	        locacao.finalizar();
	    }
	
	    public List<Locacao> listarTodas() {
	        return new ArrayList<>(locacoes);
	    }
	
	    public List<Locacao> listarAtivas() {
	
	        List<Locacao> ativas = new ArrayList<>();
	
	        for (Locacao locacao : locacoes) {
	
	            if (locacao.isAtiva()) {
	                ativas.add(locacao);
	            }
	        }
	
	        return ativas;
	    }
	
	    public int quantidadeLocacoes() {
	        return locacoes.size();
	    }
	}
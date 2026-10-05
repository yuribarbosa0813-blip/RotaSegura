package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import contrato.ComprovanteLocacao;
import contrato.RelatorioFechamento;
import excecao.DadoInvalidoException;
import modelo.Cliente;
import modelo.Locacao;
import modelo.Popular;
import modelo.Sedan;
import modelo.Suv;
import modelo.Veiculo;
import persistencia.HistoricoLocacao;
import servico.ClienteService;
import servico.FrotaService;
import servico.LocacaoService;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final FrotaService frotaService =
            new FrotaService();

    private static final ClienteService clienteService =
            new ClienteService();

    private static final LocacaoService locacaoService =
            new LocacaoService();

    public static void main(String[] args) {

        boolean executando = true;

        while (executando) {

            mostrarMenu();

            int opcao = lerInteiro("Escolha uma opção: ");

            System.out.println();

            try {

                switch (opcao) {

                    case 1:
                        cadastrarVeiculo();
                        break;

                    case 2:
                        listarVeiculos();
                        break;

                    case 3:
                        cadastrarCliente();
                        break;

                    case 4:
                        listarClientes();
                        break;

                    case 5:
                        abrirLocacao();
                        break;

                    case 6:
                        listarLocacoes();
                        break;

                    case 7:
                        devolverVeiculo();
                        break;

                    case 8:
                        emitirComprovante();
                        break;

                    case 9:
                        testarExcecoes();
                        break;

                    case 10:
                        relatorioFechamento();
                        break;

                    case 0:
                        executando = false;

                        System.out.println(
                                "Sistema encerrado."
                        );
                        break;

                    default:
                        System.out.println(
                                "Opção inválida."
                        );
                }

            } catch (DadoInvalidoException e) {

                System.out.println();
                System.out.println(
                        "ERRO: " + e.getMessage()
                );

            } catch (IOException e) {

                System.out.println();
                System.out.println(
                        "ERRO AO SALVAR: "
                        + e.getMessage()
                );
            }

            if (executando) {

                System.out.println();
                System.out.println(
                        "Pressione ENTER para continuar..."
                );

                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          ROTA SEGURA");
        System.out.println("=================================");
        System.out.println();

        System.out.println("1 - Cadastrar veículo");
        System.out.println("2 - Listar veículos");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Listar clientes");
        System.out.println("5 - Abrir locação");
        System.out.println("6 - Listar locações");
        System.out.println("7 - Devolver veículo");
        System.out.println("8 - Emitir comprovante");
        System.out.println("9 - Testar exceções");
        System.out.println("10 - Relatório de fechamento");
        System.out.println("0 - Sair");

        System.out.println();
    }

    private static void cadastrarVeiculo()
            throws DadoInvalidoException {

        System.out.println("CADASTRO DE VEÍCULO");
        System.out.println("---------------------------------");

        System.out.println("1 - Popular");
        System.out.println("2 - Sedan");
        System.out.println("3 - SUV");

        int categoria = lerInteiro(
                "Escolha a categoria: "
        );

        String placa = lerTexto("Placa: ");
        String modelo = lerTexto("Modelo: ");
        int ano = lerInteiro("Ano: ");

        double quilometragem =
                lerDouble("Quilometragem: ");

        Veiculo veiculo;

        switch (categoria) {

            case 1:

                veiculo = new Popular(
                        placa,
                        modelo,
                        ano,
                        quilometragem
                );

                break;

            case 2:

                veiculo = new Sedan(
                        placa,
                        modelo,
                        ano,
                        quilometragem
                );

                break;

            case 3:

                veiculo = new Suv(
                        placa,
                        modelo,
                        ano,
                        quilometragem
                );

                break;

            default:

                throw new DadoInvalidoException(
                        "Categoria inválida."
                );
        }

        frotaService.cadastrarVeiculo(veiculo);

        System.out.println();
        System.out.println(
                "Veículo cadastrado com sucesso!"
        );
    }

    private static void listarVeiculos() {

        List<Veiculo> veiculos =
                frotaService.listarVeiculos();

        System.out.println("VEÍCULOS CADASTRADOS");
        System.out.println("---------------------------------");

        if (veiculos.isEmpty()) {

            System.out.println(
                    "Nenhum veículo cadastrado."
            );

            return;
        }

        for (Veiculo veiculo : veiculos) {

            System.out.println(
                    "Categoria: "
                    + veiculo.getCategoria()
            );

            System.out.println(
                    "Modelo: "
                    + veiculo.getModelo()
            );

            System.out.println(
                    "Placa: "
                    + veiculo.getPlaca()
            );

            System.out.println(
                    "Ano: "
                    + veiculo.getAno()
            );

            System.out.println(
                    "Quilometragem: "
                    + veiculo.getQuilometragem()
            );

            System.out.println(
                    "Diária: R$ "
                    + String.format(
                            "%.2f",
                            veiculo.calcularDiaria()
                    )
            );

            System.out.println(
                    "Disponível: "
                    + veiculo.isDisponivel()
            );

            System.out.println("---------------------------------");
        }
    }

    private static void cadastrarCliente()
            throws DadoInvalidoException {

        System.out.println("CADASTRO DE CLIENTE");
        System.out.println("---------------------------------");

        String cpf = lerTexto("CPF: ");
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        String email = lerTexto("E-mail: ");

        Cliente cliente = new Cliente(
                cpf,
                nome,
                telefone,
                email
        );

        clienteService.cadastrarCliente(cliente);

        System.out.println();
        System.out.println(
                "Cliente cadastrado com sucesso!"
        );
    }

    private static void listarClientes() {

        List<Cliente> clientes =
                clienteService.listarClientes();

        System.out.println("CLIENTES CADASTRADOS");
        System.out.println("---------------------------------");

        if (clientes.isEmpty()) {

            System.out.println(
                    "Nenhum cliente cadastrado."
            );

            return;
        }

        for (Cliente cliente : clientes) {

            System.out.println(
                    "Nome: "
                    + cliente.getNome()
            );

            System.out.println(
                    "CPF: "
                    + cliente.getCpf()
            );

            System.out.println(
                    "Telefone: "
                    + cliente.getTelefone()
            );

            System.out.println(
                    "E-mail: "
                    + cliente.getEmail()
            );

            System.out.println("---------------------------------");
        }
    }

    private static void abrirLocacao()
            throws DadoInvalidoException {

        System.out.println("ABRIR LOCAÇÃO");
        System.out.println("---------------------------------");

        if (clienteService.quantidadeClientes() == 0) {

            System.out.println(
                    "Cadastre um cliente primeiro."
            );

            return;
        }

        if (frotaService.quantidadeVeiculos() == 0) {

            System.out.println(
                    "Cadastre um veículo primeiro."
            );

            return;
        }

        listarClientes();

        String cpf = lerTexto(
                "Informe o CPF do cliente: "
        );

        Cliente cliente =
                clienteService.buscarPorCpf(cpf);

        System.out.println();

        listarVeiculos();

        String placa = lerTexto(
                "Informe a placa do veículo: "
        );

        Veiculo veiculo =
                frotaService.buscarPorPlaca(placa);

        System.out.println();

        LocalDate dataInicio =
                lerData(
                        "Data de retirada (AAAA-MM-DD): "
                );

        LocalDate dataFim =
                lerData(
                        "Data de devolução (AAAA-MM-DD): "
                );

        Locacao locacao =
                locacaoService.criarLocacao(
                        cliente,
                        veiculo,
                        dataInicio,
                        dataFim
                );

        System.out.println();

        System.out.println(
                "Locação criada com sucesso!"
        );

        System.out.println(
                "Cliente: "
                + locacao.getCliente().getNome()
        );

        System.out.println(
                "Veículo: "
                + locacao.getVeiculo().getModelo()
        );

        System.out.println(
                "Dias: "
                + locacao.getDias()
        );

        System.out.println(
                "Valor total: R$ "
                + String.format(
                        "%.2f",
                        locacao.getValorTotal()
                )
        );
    }

    private static void listarLocacoes() {

        List<Locacao> locacoes =
                locacaoService.listarTodas();

        System.out.println("LOCAÇÕES");
        System.out.println("---------------------------------");

        if (locacoes.isEmpty()) {

            System.out.println(
                    "Nenhuma locação registrada."
            );

            return;
        }

        for (Locacao locacao : locacoes) {

            System.out.println(
                    "Cliente: "
                    + locacao.getCliente().getNome()
            );

            System.out.println(
                    "Veículo: "
                    + locacao.getVeiculo().getModelo()
            );

            System.out.println(
                    "Data de retirada: "
                    + locacao.getDataInicio()
            );

            System.out.println(
                    "Data de devolução: "
                    + locacao.getDataFim()
            );

            System.out.println(
                    "Dias: "
                    + locacao.getDias()
            );

            System.out.println(
                    "Valor total: R$ "
                    + String.format(
                            "%.2f",
                            locacao.getValorTotal()
                    )
            );

            System.out.println(
                    "Status: "
                    + (
                        locacao.isAtiva()
                        ? "Ativa"
                        : "Finalizada"
                    )
            );

            System.out.println("---------------------------------");
        }
    }

    private static void devolverVeiculo()
            throws DadoInvalidoException, IOException {

        System.out.println("DEVOLUÇÃO");
        System.out.println("---------------------------------");

        List<Locacao> locacoes =
                locacaoService.listarAtivas();

        if (locacoes.isEmpty()) {

            System.out.println(
                    "Não existem locações ativas."
            );

            return;
        }

        for (int i = 0; i < locacoes.size(); i++) {

            Locacao locacao = locacoes.get(i);

            System.out.println(
                    (i + 1)
                    + " - "
                    + locacao.getVeiculo().getModelo()
                    + " | Cliente: "
                    + locacao.getCliente().getNome()
            );
        }

        int escolha = lerInteiro(
                "Escolha a locação: "
        );

        if (escolha < 1 || escolha > locacoes.size()) {

            throw new DadoInvalidoException(
                    "Locação inválida."
            );
        }

        Locacao locacao =
                locacoes.get(escolha - 1);

        locacaoService.finalizarLocacao(locacao);

        HistoricoLocacao historico =
                new HistoricoLocacao();

        historico.salvar(locacao);

        System.out.println();

        System.out.println(
                "Veículo devolvido com sucesso!"
        );

        System.out.println(
                "Histórico salvo com sucesso."
        );
    }

    private static void emitirComprovante()
            throws DadoInvalidoException {

        System.out.println("EMITIR COMPROVANTE");
        System.out.println("---------------------------------");

        List<Locacao> locacoes =
                locacaoService.listarTodas();

        if (locacoes.isEmpty()) {

            System.out.println(
                    "Nenhuma locação registrada."
            );

            return;
        }

        for (int i = 0; i < locacoes.size(); i++) {

            Locacao locacao = locacoes.get(i);

            System.out.println(
                    (i + 1)
                    + " - "
                    + locacao.getVeiculo().getModelo()
                    + " | "
                    + locacao.getCliente().getNome()
            );
        }

        int escolha = lerInteiro(
                "Escolha a locação: "
        );

        if (escolha < 1 || escolha > locacoes.size()) {

            throw new DadoInvalidoException(
                    "Locação inválida."
            );
        }

        Locacao locacao =
                locacoes.get(escolha - 1);

        ComprovanteLocacao comprovante =
                new ComprovanteLocacao(locacao);

        System.out.println();

        comprovante.imprimirDocumento();
    }

    private static void relatorioFechamento()
            throws DadoInvalidoException {

        System.out.println("RELATÓRIO DE FECHAMENTO");
        System.out.println("---------------------------------");

        List<Locacao> locacoes =
                locacaoService.listarTodas();

        if (locacoes.isEmpty()) {

            System.out.println(
                    "Nenhuma locação registrada."
            );

            return;
        }

        for (int i = 0; i < locacoes.size(); i++) {

            Locacao locacao = locacoes.get(i);

            System.out.println(
                    (i + 1)
                    + " - "
                    + locacao.getVeiculo().getModelo()
                    + " | Cliente: "
                    + locacao.getCliente().getNome()
                    + " | Status: "
                    + (
                        locacao.isAtiva()
                        ? "Ativa"
                        : "Finalizada"
                    )
            );
        }

        System.out.println();

        int escolha = lerInteiro(
                "Escolha a locação: "
        );

        if (escolha < 1 || escolha > locacoes.size()) {

            throw new DadoInvalidoException(
                    "Locação inválida."
            );
        }

        Locacao locacao =
                locacoes.get(escolha - 1);

        RelatorioFechamento relatorio =
                new RelatorioFechamento(locacao);

        System.out.println();

        relatorio.imprimirDocumento();
    }

    private static void testarExcecoes() {

        System.out.println("TESTES DE EXCEÇÕES");
        System.out.println("---------------------------------");

        if (frotaService.quantidadeVeiculos() == 0
                || clienteService.quantidadeClientes() == 0) {

            System.out.println(
                    "Cadastre pelo menos um cliente "
                    + "e um veículo primeiro."
            );

            return;
        }

        try {

            Cliente cliente =
                    clienteService.listarClientes().get(0);

            Veiculo veiculo =
                    frotaService.listarVeiculos().get(0);

            LocalDate inicio =
                    LocalDate.now().plusDays(1);

            LocalDate fim =
                    inicio.plusDays(2);

            Locacao primeira =
                    locacaoService.criarLocacao(
                            cliente,
                            veiculo,
                            inicio,
                            fim
                    );

            System.out.println(
                    "Primeira locação criada para teste."
            );

            try {

                locacaoService.criarLocacao(
                        cliente,
                        veiculo,
                        inicio,
                        fim
                );

                System.out.println(
                        "ERRO: veículo ocupado foi aceito."
                );

            } catch (DadoInvalidoException e) {

                System.out.println(
                        "Teste de veículo ocupado: OK"
                );

                System.out.println(
                        "Mensagem: "
                        + e.getMessage()
                );
            }

            locacaoService.finalizarLocacao(
                    primeira
            );

        } catch (DadoInvalidoException e) {

            System.out.println(
                    "Não foi possível executar "
                    + "o teste de veículo ocupado."
            );

            System.out.println(
                    "Mensagem: "
                    + e.getMessage()
            );
        }

        try {

            Cliente cliente =
                    clienteService.listarClientes().get(0);

            Veiculo veiculo =
                    frotaService.listarVeiculos().get(0);

            LocalDate inicio =
                    LocalDate.now().plusDays(5);

            LocalDate fim =
                    LocalDate.now().plusDays(2);

            locacaoService.criarLocacao(
                    cliente,
                    veiculo,
                    inicio,
                    fim
            );

            System.out.println(
                    "ERRO: datas inválidas foram aceitas."
            );

        } catch (DadoInvalidoException e) {

            System.out.println(
                    "Teste de datas inválidas: OK"
            );

            System.out.println(
                    "Mensagem: "
                    + e.getMessage()
            );
        }
    }

    private static String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                int valor =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                return valor;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um número válido."
                );
            }
        }
    }

    private static double lerDouble(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                String entrada =
                        scanner.nextLine().replace(",", ".");

                return Double.parseDouble(entrada);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um número válido."
                );
            }
        }
    }

    private static LocalDate lerData(String mensagem)
            throws DadoInvalidoException {

        try {

            System.out.print(mensagem);

            return LocalDate.parse(
                    scanner.nextLine()
            );

        } catch (DateTimeParseException e) {

            throw new DadoInvalidoException(
                    "Data inválida. Use o formato AAAA-MM-DD."
            );
        }
    }
}
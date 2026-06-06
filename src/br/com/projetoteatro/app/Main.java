package br.com.projetoteatro.app;

import br.com.projetoteatro.exceptions.AdiministradorInvalidoException;
import br.com.projetoteatro.exceptions.ContratanteInvalidoException;
import br.com.projetoteatro.exceptions.SenhaInvalidaException;
import br.com.projetoteatro.model.*;
import br.com.projetoteatro.service.EmailService;
import br.com.projetoteatro.service.LoginService;
import br.com.projetoteatro.service.ServicoTeatro;
import br.com.projetoteatro.service.ServicoTeatro;
import br.com.projetoteatro.repository.Persistencia;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static ServicoTeatro central;
    private static Persistencia persistencia;
    private static final String ARQUIVO_CENTRAL = "central.xml";
    public static void main(String[] args) {
        persistencia = new Persistencia();
        central = persistencia.recuperarCentral(ARQUIVO_CENTRAL);

        Scanner input = new Scanner(System.in);

        String opcao = "";

        do {
            System.out.println("1-Cadastrar adm");
            System.out.println("2-Fazer Login");
            System.out.println("3-Solicitar mudança senha");
            System.out.println("4-Cadastrar Artista");
            System.out.println("5-Fazer Login Artista");
            System.out.println("6-Solicitar mudança senha Artista");
            System.out.println("7-Mostrar lista de  Artistas");
            System.out.println("8-Excluir artista da lista");
            System.out.println("9-Cadastrar cliente");
            System.out.println("10-Fazer Login Cliente");
            System.out.println("11-Solicitar mudança senha Cliente");
            System.out.println("12-Mostrar lista de  Cliente");
            System.out.println("13-Excluir artista da Cliente");
            System.out.println("14-Cadastrar Proposta de Aluguel");
            System.out.println("15-Listar Propostas");
            System.out.println("16-Detalhar/Promover Proposta");
            System.out.println("17-Enviar Proposta por Email");
            System.out.println("S-Sair");
            opcao=input.nextLine();
            LoginService login=central.getLoginService();

            switch (opcao){
                case "1"://cadastrando adm
                    System.out.println("Digite seu nome: ");
                    String nomeAdm= input.nextLine();
                    System.out.println("Digite seu email: ");
                    String emailAdm= input.nextLine();
                    System.out.println("Digite seu telefone: ");
                    String telefoneAdm= input.nextLine();
                    System.out.println("Digite seu cpf: ");
                    String cpfAdm= input.nextLine();
                    System.out.println("Digite sua senha: ");
                    String senhaAdm= input.nextLine();
                    try{
                        central.getAdministradorService().getAdm();
                    }catch (AdiministradorInvalidoException e){
                        Administrador adm=new Administrador(nomeAdm,emailAdm,telefoneAdm,cpfAdm,senhaAdm);
                        central.getAdministradorService().cadastrarAdministrador(adm);
                        persistencia.salvarCentral(central,ARQUIVO_CENTRAL);
                    }
                    //cadastrar artista e cliente
                    break;

                case "2"://fazendo login

                    System.out.print("Email: ");
                    String email = input.nextLine();
                    System.out.print("Senha: ");
                    String senha = input.nextLine();

                    try {

                        Pessoa pessoa = login.autenticar(email, senha);
                        if(pessoa instanceof Administrador){
                            Administrador admLogado = (Administrador) pessoa;
                            System.out.println("Bem-vindo " + admLogado.getNome());
                        }else{
                            System.out.println("Esse usuário não é administrador.");
                        }
                        //entrei na tela adm...

                    } catch (AdiministradorInvalidoException e) {
                        e.printStackTrace();
                        break;
                        ///case3
                    }
                    break;

                case "3"://solicitando alteração senha
                    try{
                        System.out.print("Confirme seu cpf para alterar senha: ");
                        String cpfprocurado = input.nextLine();
                        central.getLoginService().solicitarMudancaSenha(cpfprocurado);

                        System.out.print("CPF: ");
                        String cpf = input.nextLine();
                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.getLoginService().redefinirSenha(cpf, codigo, novaSenha);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4"://cadastrando artista
                    System.out.println("Digite seu nome: ");
                    String nomeArtista= input.nextLine();
                    System.out.println("Digite seu email: ");
                    String emailArtista= input.nextLine();
                    System.out.println("Digite seu telefone: ");
                    String telefoneArtista= input.nextLine();
                    System.out.println("Digite seu cpf: ");
                    String cpfArtista= input.nextLine();
                    System.out.println("Digite sua senha: ");
                    String senhaArtista= input.nextLine();
                    try{
                        Contratante contratante =new Contratante(nomeArtista,emailArtista,telefoneArtista,cpfArtista,senhaArtista);
                        central.getArtistaService().cadastrarContratante(contratante);
                        persistencia.salvarCentral(central,ARQUIVO_CENTRAL);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "5"://fazer login artista
                    System.out.print("Email: ");
                    emailArtista = input.nextLine();
                    System.out.print("Senha: ");
                    senhaArtista = input.nextLine();

                    try{
                        Contratante artista=(Contratante)login.autenticar(emailArtista,senhaArtista);
                        System.out.println("Bem-vindo " + artista.getNome());
                    }catch (Exception e) {
                        e.printStackTrace();}
                    break;

                case "6"://solicitar mudança de senha
                    try{
                        System.out.print("Confirme seu cpf para alterar senha: ");
                        String cpfArtistaprocurado = input.nextLine();
                        central.getLoginService().solicitarMudancaSenha(cpfArtistaprocurado);


                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.getLoginService().redefinirSenha(cpfArtistaprocurado,codigo,novaSenha);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "7"://listar artista

                    for(Contratante c : central.getArtistaService().getListaContratante()){
                        System.out.println(c);
                    }

                    break;
                case "8":

                    System.out.print("CPF do Artista: ");
                    String cpfArtistaProcurado = input.nextLine();
                    try{
                        central.getArtistaService().excluirContratante(cpfArtistaProcurado);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                        System.out.println("Cliente removido!");

                        }catch(Exception e){
                        e.printStackTrace();
                    }

                    break;
                case "9"://cadastrando Cliente
                    System.out.println("Digite seu nome: ");
                    String nomeCliente= input.nextLine();
                    System.out.println("Digite seu email: ");
                    String emailCliente= input.nextLine();
                    System.out.println("Digite seu telefone: ");
                    String telefoneCliente= input.nextLine();
                    System.out.println("Digite seu cpf: ");
                    String cpfCliente= input.nextLine();
                    System.out.println("Digite sua senha: ");
                    String senhaCliente= input.nextLine();
                    try{
                        Usuario cliente =new Usuario(nomeCliente,emailCliente,telefoneCliente,cpfCliente,senhaCliente);
                        central.getClienteService().cadastrarCliente(cliente);
                        persistencia.salvarCentral(central,ARQUIVO_CENTRAL);

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    break;

                case "10"://login cliente
                    System.out.print("Email: ");
                    emailCliente = input.nextLine();
                    System.out.print("Senha: ");
                    senhaCliente = input.nextLine();

                    try{
                        Usuario cliente=(Usuario)login.autenticar(emailCliente,senhaCliente);
                        System.out.println("Bem-vindo " + cliente.getNome());
                    }catch (Exception e) {
                        e.printStackTrace();}
                    break;

                case "11"://solicitar mudança senha cliente
                    try{
                        System.out.print("Confirme seu cpf para alterar senha: ");
                        String cpfClienteprocurado = input.nextLine();
                        central.getLoginService().solicitarMudancaSenha(cpfClienteprocurado);

                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.getLoginService().redefinirSenha(cpfClienteprocurado,codigo,novaSenha);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case "12"://listar cliente

                    for(Usuario c : central.getClienteService().getListaCliente()){
                        System.out.println(c);
                    }

                    break;
                case "13":

                    System.out.print("CPF do cliente: ");
                    String cpfClienteProcurado = input.nextLine();
                    try{
                        central.getClienteService().excluirCliente(cpfClienteProcurado);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                        System.out.println("Cliente removido!");

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    break;

                case "14"://cadastrar proposta

                    try {

                        Contratante artista;

                        System.out.print("CPF do artista responsável: ");
                        String cpfArtistaa = input.nextLine();

                        try {

                            artista = central.getArtistaService()
                                    .buscarContratante(cpfArtistaa);

                            System.out.println(
                                    "Artista já cadastrado. Dados reutilizados."
                            );

                        } catch (ContratanteInvalidoException e) {

                            System.out.println(
                                    "Primeiro aluguel deste artista."
                            );

                            System.out.print("Nome: ");
                            String nome = input.nextLine();

                            System.out.print("Email: ");
                            String emaill = input.nextLine();

                            System.out.print("Telefone: ");
                            String telefone = input.nextLine();

                            System.out.print("Senha: ");
                            String senhaa = input.nextLine();

                            artista = new Contratante(
                                    nome,
                                    emaill,
                                    telefone,
                                    cpfArtistaa,
                                    senhaa
                            );

                            central.getArtistaService()
                                    .cadastrarContratante(artista);
                        }

                        System.out.print("Nome da peça: ");
                        String nomePeca = input.nextLine();

                        System.out.print("Valor do aluguel: ");
                        double valorAluguel =
                                Double.parseDouble(input.nextLine());

                        System.out.print("Valor do ingresso: ");
                        double valorIngresso =
                                Double.parseDouble(input.nextLine());

                        DateTimeFormatter formatoData =
                                DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        System.out.print(
                                "Data início (dd/MM/yyyy): "
                        );

                        LocalDate dataInicio =
                                LocalDate.parse(
                                        input.nextLine(),
                                        formatoData
                                );

                        System.out.print(
                                "Data fim (dd/MM/yyyy): "
                        );

                        LocalDate dataFim =
                                LocalDate.parse(
                                        input.nextLine(),
                                        formatoData
                                );

                        System.out.print(
                                "Horário início (HH:mm): "
                        );

                        LocalTime horarioInicio =
                                LocalTime.parse(input.nextLine());

                        System.out.print(
                                "Horário fim (HH:mm): "
                        );

                        LocalTime horarioFim =
                                LocalTime.parse(input.nextLine());

                        PropostaAluguel proposta =
                                new PropostaAluguel(
                                        artista,
                                        nomePeca,
                                        valorAluguel,
                                        dataInicio,
                                        dataFim,
                                        horarioInicio,
                                        horarioFim,
                                        valorIngresso
                                );

                        central.getPropostaService()
                                .cadastrarProposta(proposta);

                        persistencia.salvarCentral(
                                central,
                                ARQUIVO_CENTRAL
                        );

                        System.out.println(
                                "Proposta cadastrada com sucesso!"
                        );

                        System.out.println(
                                "ID da proposta: "
                                        + proposta.getId()
                        );

                    } catch (Exception e) {

                        System.out.println(
                                "Erro ao cadastrar proposta: "
                                        + e.getMessage()
                        );
                    }

                    break;
                case "15"://listar proposta

                    for(PropostaAluguel p :
                            central.getPropostaService().getListaPropostas()) {

                        System.out.println(p);
                    }

                    break;
                case "16":

                    try {

                        System.out.print(
                                "ID da proposta: "
                        );

                        long id =
                                Long.parseLong(
                                        input.nextLine()
                                );

                        PropostaAluguel proposta =
                                central.getPropostaService().buscarProposta(id);

                        System.out.println(
                                "Peça: "
                                        + proposta.getNomePeca()
                        );

                        System.out.println(
                                "Artista: "
                                        + proposta.getContratante().getNome()
                        );

                        System.out.println(
                                "Período: "
                                        + proposta.getDataInicio()
                                        + " até "
                                        + proposta.getDataFim()
                        );

                        System.out.println(
                                "Horário: "
                                        + proposta.getHorarioInicio()
                                        + " às "
                                        + proposta.getHorarioFim()
                        );

                        System.out.println(
                                "Valor aluguel: "
                                        + proposta.getValorAluguel()
                        );

                        System.out.println(
                                "Valor ingresso: "
                                        + proposta.getValorIngresso()
                        );

                        System.out.println(
                                "Status atual: "
                                        + proposta.getStatusProposta()
                        );

                        System.out.println();
                        System.out.println(
                                "1 - Aprovar proposta"
                        );
                        System.out.println(
                                "2 - Encerrar contrato"
                        );
                        System.out.println(
                                "3 - Estender contrato"
                        );

                        String escolha =
                                input.nextLine();

                        switch (escolha) {

                            case "1":

                                proposta.contratar();

                                System.out.println(
                                        "Proposta aprovada."
                                );

                                break;

                            case "2":

                                proposta.encerrarContrato();

                                System.out.println(
                                        "Contrato encerrado."
                                );

                                break;

                            case "3":

                                DateTimeFormatter formato =
                                        DateTimeFormatter
                                                .ofPattern(
                                                        "dd/MM/yyyy"
                                                );

                                System.out.print(
                                        "Nova data final: "
                                );

                                LocalDate novaData =
                                        LocalDate.parse(
                                                input.nextLine(),
                                                formato
                                        );

                                proposta.estenderContrato(
                                        novaData
                                );

                                System.out.println(
                                        "Contrato alterado."
                                );

                                break;
                        }

                        persistencia.salvarCentral(
                                central,
                                ARQUIVO_CENTRAL
                        );

                    } catch(Exception e) {

                        System.out.println(
                                e.getMessage()
                        );
                    }

                    break;
                case "17":

                    try {

                        System.out.print("ID da proposta: ");
                        long id = Long.parseLong(input.nextLine());

                        boolean enviado =
                                central.getPropostaService()
                                        .enviarPropostaPorEmail(id);

                        if(enviado){
                            System.out.println("Email enviado com sucesso!");
                        }else{
                            System.out.println("Falha ao enviar email.");
                        }

                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    break;

            }

        }while(!"s".equalsIgnoreCase(opcao));

    }
}

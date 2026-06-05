package br.com.projetoteatro.app;

import br.com.projetoteatro.exceptions.AdiministradorInvalidoException;
import br.com.projetoteatro.exceptions.ContratanteInvalidoException;
import br.com.projetoteatro.exceptions.SenhaInvalidaException;
import br.com.projetoteatro.model.Administrador;
import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.Usuario;
import br.com.projetoteatro.service.LoginService;
import br.com.projetoteatro.service.ServicoTeatro;
import br.com.projetoteatro.service.ServicoTeatro;
import br.com.projetoteatro.repository.Persistencia;


import java.util.Scanner;

public class Main {

    private static ServicoTeatro central;
    private static Persistencia persistencia;
    private static final String ARQUIVO_CENTRAL = "central.xml";
    public static void main(String[] args) {
        persistencia = new Persistencia();
        central = persistencia.recuperarCentral(ARQUIVO_CENTRAL);

        Scanner input = new Scanner(System.in);
        LoginService login=new LoginService();
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
            System.out.println("S-Sair");
            opcao=input.nextLine();

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
                        central.getAdm();
                    }catch (AdiministradorInvalidoException e){
                        Administrador adm=new Administrador(nomeAdm,emailAdm,telefoneAdm,cpfAdm,senhaAdm);
                        central.cadastrarAdministrador(adm);
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

                        Administrador admLogado = login.autenticarAdm(email, senha, central.getAdm());
                        System.out.println("Bem-vindo " + central.getAdm().getNome());
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
                        central.solicitarMudancaSenhaAdm(cpfprocurado);

                        System.out.print("CPF: ");
                        String cpf = input.nextLine();
                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.redefinirSenhaAdm(cpf, codigo, novaSenha);
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
                        central.cadastrarContratante(contratante);
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
                        Contratante artista=login.autenticarArtista(emailArtista,senhaArtista,central.getListaContratante());
                        System.out.println("Bem-vindo " + artista.getNome());
                    }catch (Exception e) {
                        e.printStackTrace();}
                    break;

                case "6"://solicitar mudança de senha
                    try{
                        System.out.print("Confirme seu cpf para alterar senha: ");
                        String cpfArtistaprocurado = input.nextLine();
                        central.solicitarMudancaSenhaContratante(cpfArtistaprocurado);


                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.redefinirSenhaContratante(cpfArtistaprocurado,codigo,novaSenha);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "7"://listar artista

                    for(Contratante c : central.getListaContratante()){
                        System.out.println(c);
                    }

                    break;
                case "8":

                    System.out.print("CPF do cartista: ");
                    String cpfArtistaProcurado = input.nextLine();
                    try{
                        central.excluirContratante(cpfArtistaProcurado);
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
                        central.cadastrarCliente(cliente);
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
                        Usuario cliente=login.autenticarUsuario(emailCliente,senhaCliente,central.getListaCliente());
                        System.out.println("Bem-vindo " + cliente.getNome());
                    }catch (Exception e) {
                        e.printStackTrace();}
                    break;

                case "11"://solicitar mudança senha cliente
                    try{
                        System.out.print("Confirme seu cpf para alterar senha: ");
                        String cpfClienteprocurado = input.nextLine();
                        central.solicitarMudancaSenha(cpfClienteprocurado);

                        System.out.print("Código recebido: ");
                        String codigo = input.nextLine();
                        System.out.print("Nova senha: ");
                        String novaSenha = input.nextLine();

                        central.redefinirSenha(cpfClienteprocurado,codigo,novaSenha);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case "12"://listar cliente

                    for(Usuario c : central.getListaCliente()){
                        System.out.println(c);
                    }

                    break;
                case "13":

                    System.out.print("CPF do cliente: ");
                    String cpfClienteProcurado = input.nextLine();
                    try{
                        central.excluirCliente(cpfClienteProcurado);
                        persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                        System.out.println("Cliente removido!");

                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    break;

            }

        }while(!"s".equalsIgnoreCase(opcao));

    }
}

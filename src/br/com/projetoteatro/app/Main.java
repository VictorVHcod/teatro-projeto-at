package br.com.projetoteatro.app;

import br.com.projetoteatro.exceptions.AdiministradorInvalidoException;
import br.com.projetoteatro.exceptions.SenhaInvalidaException;
import br.com.projetoteatro.model.Administrador;
import br.com.projetoteatro.service.LoginService;
import br.com.projetoteatro.service.validators.ServicoTeatro;


import java.util.Scanner;

public class Main {

    private static ServicoTeatro central;
   // private static Persistencia persistencia;
    private static final String ARQUIVO_CENTRAL = "central.xml";
    public static void main(String[] args) {
        //persistencia = new Persistencia();
        //central = persistencia.recuperarCentral(ARQUIVO_CENTRAL);

        Scanner input = new Scanner(System.in);
        LoginService login=new LoginService();
        String opcao = "";

        while (!opcao.equalsIgnoreCase("s")){
            System.out.println("1-Cadastrar");
            System.out.println("2-Fazer Login");
            System.out.println("S-Sair");
            opcao=input.nextLine();
            try {
                switch (opcao){
                    case "1":
                        System.out.println("Digite seu nome: ");
                        String nome= input.nextLine();
                        System.out.println("Digite seu email: ");
                        String email= input.nextLine();
                        System.out.println("Digite seu telefone: ");
                        String telefone= input.nextLine();
                        System.out.println("Digite seu cpf: ");
                        String cpf= input.nextLine();
                        System.out.println("Digite sua senha: ");
                        String senha= input.nextLine();
                        try{
                          //  central.getAdm();
                        }catch (AdiministradorInvalidoException e){
                            Administrador adm=new Administrador(nome,email,telefone,cpf,senha);
                          //  central.cadastrarAdministrador(adm);
                          //  persistencia.salvarCentral(central,ARQUIVO_CENTRAL);
                        }
                             //cadastrar artista e cliente
                        break;

                    case "2":
                        System.out.print("Digite sua categoria: Contratante/administrador/cliente: ");
                        String categoria = input.nextLine().toLowerCase();
                        System.out.print("Email: ");
                        email = input.nextLine();
                        System.out.print("Senha: ");
                        senha = input.nextLine();

                            try {

                               // Administrador admLogado = login.autenticarAdm(email, senha, central.getAdm());
                               // System.out.println("Bem-vindo " + central.getAdm().getNome());
                                //entrei na tela adm...

                            } catch (AdiministradorInvalidoException e) {
                                e.printStackTrace();
                                break;
                                ///case3
                            } catch (SenhaInvalidaException e) {
                                e.printStackTrace();
                                System.out.print("Confirme seu cpf para alterar senha: ");
                                String cpfprocurado = input.nextLine();
                               // central.solicitarMudancaSenhaAdm(cpfprocurado);
                            }
                            try {
                                System.out.print("CPF: ");
                                cpf = input.nextLine();
                                System.out.print("Código recebido: ");
                                String codigo = input.nextLine();
                                System.out.print("Nova senha: ");
                                String novaSenha = input.nextLine();

                               // central.redefinirSenhaAdm(cpf, codigo, novaSenha);
                              //  persistencia.salvarCentral(central, ARQUIVO_CENTRAL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }



                }

                } catch (Exception e) {
                e.printStackTrace();
            }

        }while("s".equalsIgnoreCase(opcao));

    }
}

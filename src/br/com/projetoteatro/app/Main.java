package br.com.projetoteatro.app;

import br.com.projetoteatro.model.Administrador;
import br.com.projetoteatro.repository.AdministradorRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AdministradorRepository repository = new AdministradorRepository();
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Email: ");
            String email = input.nextLine();

            System.out.println("Senha: ");
            String senha = input.nextLine();

            System.out.println("EMAIL: " + email);
            System.out.println("SENHA: " + senha);
            Administrador admin = new Administrador(email, senha);
            repository.salvar(admin);

            System.out.println(repository.buscaLogin(email,senha));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

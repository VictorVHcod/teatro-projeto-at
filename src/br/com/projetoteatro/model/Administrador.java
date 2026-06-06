package br.com.projetoteatro.model;

public class Administrador extends Pessoa {
    private String senha;
    public Administrador() {

    }
    public Administrador(String email, String senha) {
        super(email);
        this.senha = senha;
    }

    public Administrador(String nome, String email, String senha) {
        super(nome, email);
        this.senha = senha;
    }
    public Administrador(String nome, String email, String telefone, String cpf, String senha) {
        super(nome, email, telefone, cpf);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

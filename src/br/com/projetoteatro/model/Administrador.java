package br.com.projetoteatro.model;

public class Administrador extends Pessoa {
    private String senha;

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
    //talvez seja mais interessante deixar em uma classe separada ---
    public boolean autenticacao(String email,String senha) {
        return this.getEmail().equals(email) && this.senha.equals(senha);
    }
}

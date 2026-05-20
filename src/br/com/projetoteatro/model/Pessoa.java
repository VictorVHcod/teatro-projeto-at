package br.com.projetoteatro.model;

public abstract class Pessoa {
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    // em breve um atributo para ver idade, será com local date, vou estudar um pouquinho ainda.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa() {

    }

    public Pessoa(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + System.lineSeparator() +
                "E-mail: " + getEmail() + System.lineSeparator() +
                "Telefone: " + getTelefone() + System.lineSeparator() +
                "CPF: " + getCpf();

    }
}

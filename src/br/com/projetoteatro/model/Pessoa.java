package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.Genero;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
    private Genero sexo;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    public Pessoa(String email) {
        this.email = email;
    }

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Pessoa(String nome, String email, String telefone, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    public Pessoa(String nome, String cpf, String email, Genero sexo, LocalDate dataNascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.sexo= sexo;
        this.dataNascimento=dataNascimento;

    }

    //public boolean verficadorIdade() {
     //   return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
  //  }

    @Override
    public String toString() {
        return "Nome: " + getNome() + System.lineSeparator() +
                "E-mail: " + getEmail() + System.lineSeparator() +
                "Telefone: " + getTelefone() + System.lineSeparator() +
                "CPF: " + getCpf() + System.lineSeparator();
                //"Maior idade: " + verficadorIdade();

    }
}

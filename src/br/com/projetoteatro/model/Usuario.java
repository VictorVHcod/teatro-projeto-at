package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.Genero;

import java.time.LocalDate;

//Classe usuario final expectador
public class Usuario extends Pessoa{
    private String senha;
    private long id;
    public Usuario(){
        this.id = System.currentTimeMillis();

    }

    public Usuario(String email,String senha){
        super();
        this.id=System.currentTimeMillis();
        this.senha = senha;

    }

    public Usuario(String nome, String CPF, String email, Genero sexo, LocalDate dataNascimento, String telefone, String senha) {
        super( nome,CPF,email,sexo,dataNascimento,telefone);
        this.id=System.currentTimeMillis();
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getId() {
        return id;
    }


}

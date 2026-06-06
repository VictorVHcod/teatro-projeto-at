package br.com.projetoteatro.model;

public class Contratante extends Pessoa {
     private long id;

    public Contratante(String nome) {
        this.setNome(nome);
    }

 public Contratante(String nome, String email, String telefone, String cpf) {
  super(nome, email, telefone, cpf);
  this.id = System.currentTimeMillis();
 }

}

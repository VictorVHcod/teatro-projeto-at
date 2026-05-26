package br.com.projetoteatro.model;

public class Contratante extends Pessoa {
 private Pessoa pessoa;
     private long id;


 public Contratante(String nome, String email, String telefone, String cpf) {
  super(nome, email, telefone, cpf);
  this.id = System.currentTimeMillis();
 }

}

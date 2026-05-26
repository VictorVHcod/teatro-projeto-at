package br.com.projetoteatro.model;
//Classe usuario final expectador
public class Usuario extends Pessoa{
    private long id;

    public Usuario(String nome, String email, String telefone, String cpf) {
        super(nome, email, telefone, cpf);
        this.id = System.currentTimeMillis();
    }


    public long getId() {
        return id;
    }


}

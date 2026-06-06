package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.StatusAssento;

public class Assento {
    private String codigo;
    private StatusAssento status;
    private Setor setor;

    public Assento(String numeroAssento) {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        this.codigo = codigo;
    }

    public StatusAssento getStatus() {
        return status;
    }

    public void setStatus(StatusAssento status) {
        this.status = status;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Assento() {

    }

    public Assento(String codigo,StatusAssento status, Setor setor) {
        this.codigo = codigo;
        this.status = status;
        this.setor = setor;
    }

    public String toString() {
        return "Codigo: " + codigo + System.lineSeparator() +
                "Status: " + status + System.lineSeparator() +
                "Setor: " + setor.getSetor();
    }
}

package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.TipoSetor;

import java.util.List;

import java.util.ArrayList;

public class Setor {
    private TipoSetor tipoSetor;
    private double valor;
    private int capacidade;
    private List<Assento> assentos;

    public TipoSetor getSetor() {
        return tipoSetor;
    }

    public void setSetor(TipoSetor setor) {
        this.tipoSetor = setor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public void setAssentos(List<Assento> assento) {
        this.assentos = assento;
    }

    public Setor() {

    }

    public Setor(TipoSetor setor,double valor,int capacidade) {
        this.tipoSetor = setor;
        this.valor = valor;
        this.capacidade = capacidade;
        this.assentos = new ArrayList<>();
    }

    public String toString() {
        return "Setor" + tipoSetor + System.lineSeparator() +
                "Valor: " + valor + System.lineSeparator() +
                "Capacidade: " + capacidade;
    }
}

package br.com.projetoteatro.model;

import java.time.LocalDate;
import java.util.List;

public class Peca {
    private long id;
    private String nome;
    private Artista artistaResponsavel;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double precoIngresso;
    private StatusContrato status;
    private List<Sessao> sessoes;
    private double valorAluguel;

    public void adicionarSessao(Sessao sessao){

    }

    public boolean estaEncerrada(){

    }

    public void encerrarContrato(){

    }

    public void estenderContrato(LocalDate novaData {

    }

    public double calcularArrecadacao(){

    }

}
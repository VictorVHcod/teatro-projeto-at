package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.StatusProposta;

import java.time.LocalDate;
import java.time.LocalTime;

public class PropostaAluguel {
    private long id;
    private Contratante contratante;
    private String nomePeca;
    private float valorIngresso;
    private float valorAluguel;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private StatusProposta statusProposta;

    public PropostaAluguel( Contratante contratante, String nomePeca, float valorAluguel,
                           LocalDate dataInicio, LocalDate dataFim, LocalTime horarioInicio,LocalTime horarioFim,float valorIngresso) {
        this.id =System.currentTimeMillis();
        this.contratante = contratante;
        this.nomePeca = nomePeca;
        this.valorAluguel = valorAluguel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.statusProposta = StatusProposta.EM_CONTRATACAO;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valorIngresso = valorIngresso;
    }
    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    //getters setter


    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public float getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(float valorIngresso) {
        this.valorIngresso = valorIngresso;
    }


    public String toString() {
        return "PropostaAluguel{" +"id=" + id +", contratante=" + contratante.getNome() +", nomePeca='" + nomePeca + '\'' + ", status=" + statusProposta +'}';
    }
}

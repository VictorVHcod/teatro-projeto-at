package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.StatusProposta;

import java.time.LocalDate;

public class PropostaAluguel {
    private long id;
    private Contratante contratante;
    private String nomePeça;
    private float valorAluguel;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusProposta statusProposta;

    public PropostaAluguel(long id, Contratante contratante, String nomePeça, float valorAluguel,
                           LocalDate dataInicio, LocalDate dataFim, StatusProposta statusProposta) {
        this.id = id;
        this.contratante = contratante;
        this.nomePeça = nomePeça;
        this.valorAluguel = valorAluguel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.statusProposta = statusProposta;
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

    public String getNomePeça() {
        return nomePeça;
    }

    public void setNomePeça(String nomePeça) {
        this.nomePeça = nomePeça;
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}

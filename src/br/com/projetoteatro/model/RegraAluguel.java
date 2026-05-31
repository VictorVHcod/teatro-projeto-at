package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.DiasDaSemana;
import br.com.projetoteatro.enums.Turno;

import java.time.LocalTime;

public class RegraAluguel {
    private long id;
    private float valorHora;
    private DiasDaSemana diaDaSemana;
    private Turno turno;
    private String data;
    private LocalTime horarioComeco;
    private LocalTime horarioFim;

    public RegraAluguel(float valorHora, DiasDaSemana diaDaSemana, Turno turno,String data ){
        this.id=System.currentTimeMillis();
        this.valorHora=valorHora;
        this.diaDaSemana=diaDaSemana;
        this.turno=turno;
        this.data=data;

    }
    //Getter and Setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public DiasDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiasDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public LocalTime getHorarioComeco() {
        return horarioComeco;
    }








}

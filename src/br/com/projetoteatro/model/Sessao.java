package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.Turno;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Sessao {
    private long id;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Turno turno;
    private Peca peca;
    private List<Ingresso> ingressos;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }
    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }
    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Peca getPeca() {
        return peca;
    }
    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }
    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public boolean conflitaCom(Sessao outra){
            if (!this.data.equals(outra.data)) {
                return false;
            }
            return this.horarioInicio.isBefore(outra.horarioFim)
                    && this.horarioFim.isAfter(outra.horarioInicio);

    }

    public boolean estaDentroDoTurno(){
        switch (turno) {
            case MANHA:
                return !horarioInicio.isBefore(LocalTime.of(8, 0))
                        && !horarioFim.isAfter(LocalTime.of(12, 0));

            case TARDE:
                return !horarioInicio.isBefore(LocalTime.of(13, 0))
                        && !horarioFim.isAfter(LocalTime.of(18, 0));

            case NOITE:
                return !horarioInicio.isBefore(LocalTime.of(19, 0))
                        && !horarioFim.isAfter(LocalTime.of(23, 0));
            default:
                return false;
        }
    }
}

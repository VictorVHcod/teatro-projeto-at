package br.com.projetoteatro.model;

import br.com.projetoteatro.enums.Turno;
import java.time.LocalDate;
import java.time.LocalTime;

public class Sessao {
    private long id;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Turno turno;
    private Peca peca;
    //private List<Ingresso> ingressos;

    public boolean conflitaCom(Sessao outra){
            if (!this.data.equals(outra.data)) {
                return false;
            }
            return this.horarioInicio.isBefore(outra.horarioFim)
                    && this.horarioFim.isAfter(outra.horarioInicio);

    }

    public boolean estaDentroDoTurno(){
        return false;
    }
}

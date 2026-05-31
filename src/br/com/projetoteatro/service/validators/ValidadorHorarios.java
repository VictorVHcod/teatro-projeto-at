package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.exceptions.ConflitoHorarioException;
import br.com.projetoteatro.model.PropostaAluguel;

import java.util.ArrayList;

public class ValidadorHorarios {

    public void validarConflitoHorario(PropostaAluguel propostaNova, ArrayList<PropostaAluguel> listaPropostas)throws ConflitoHorarioException{
        boolean dia;
        boolean horario;
        for(PropostaAluguel atual: listaPropostas){

            dia=atual.getDataInicio().equals(propostaNova.getDataInicio());
            //se o horario da proposta nova inicia antes do horario de fim de umas das propostas cadastradas
            //e o horario da proposta nova finaliza depois do horario de inicio de uma das propostas
            //quer dizer que há conflito de horario
            horario=propostaNova.getHorarioInicio().isBefore(atual.getHorarioFim()) &&
            propostaNova.getHorarioFim().isAfter(atual.getHorarioInicio());
            if(dia && horario){
                throw new ConflitoHorarioException("Conflito de horário....");
            }
        }
    }
}

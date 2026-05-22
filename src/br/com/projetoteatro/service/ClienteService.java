package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.CPFInvalidoException;

public class ClienteService {

    public void verificadorCPF(String cpf) throws CPFInvalidoException {
        if(!ValidadorCPF.isValido(cpf)) {
           throw new CPFInvalidoException("CPF inválido") ;
        }
    }
}

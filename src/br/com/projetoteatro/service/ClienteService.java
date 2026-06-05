package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.exceptions.CPFInvalidoException;

public class ClienteService {

    public void verificadorCPF(String cpf) throws CPFInvalidoException {

        if(cpf == null) {
            throw new CPFInvalidoException("O campo do CPF não pode ser nulo");
        }

        if(!ValidadorCPF.isValido(cpf)) {
           throw new CPFInvalidoException("CPF inválido") ;
        }
    }
}

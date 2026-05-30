package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.exceptions.EmailInvalidoException;

public class ValidadorEmail {

    public static boolean validarEmail(String email) throws EmailInvalidoException {

        if(email == null) {
            throw new EmailInvalidoException("Digite um email válido no campo");
        }

        if(email.isBlank()) {
            throw new EmailInvalidoException("O campo não pode está vazio!");
        }

        int contadorArroba = 0;

        for(int i = 0; i < email.length(); i++) {
            char caracter = email.charAt(i);

            if(caracter == '@') {
                contadorArroba++;
            }
        }

        if(contadorArroba != 1) {
            throw new EmailInvalidoException("Digite um email válido!");
        }

        if(email.charAt(0) == '@') {
            throw new EmailInvalidoException("Um email não pode ser iniciado com @");
        }

        if(email.charAt(email.length() - 1) == '@') {
            throw new EmailInvalidoException("Um email não pode ser finalizado com @");
        }

        if(!email.contains(".")) {
            throw new EmailInvalidoException("Digite um email válido!");
        }


        return true;
    }
}

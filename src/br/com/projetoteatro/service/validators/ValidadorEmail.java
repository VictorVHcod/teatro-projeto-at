package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.exceptions.EmailInvalidoException;

public class ValidadorEmail {

    public static boolean validarEmail(String email) throws EmailInvalidoException {
        //1. Não pode ser null
        if(email == null) {
            throw new EmailInvalidoException("Digite um email válido no campo");
        }
        //2. Não pode estar vazio
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
        //3. Deve possuir exatamente 1 @
        if(contadorArroba != 1) {
            throw new EmailInvalidoException("Digite um email válido!");
        }
        //5. Não pode começar com @
        if(email.charAt(0) == '@') {
            throw new EmailInvalidoException("Um email não pode ser iniciado com @");
        }
        //6. Não pode terminar com @
        if(email.charAt(email.length() - 1) == '@') {
            throw new EmailInvalidoException("Um email não pode ser finalizado com @");
        }
        //* 7. Deve possuir pelo menos um .
        if(!email.contains(".")) {
            throw new EmailInvalidoException("Digite um email válido!");
        }

        int posicaoArroba = email.indexOf('@');

        String antesArroba = email.substring(0, posicaoArroba);

        if(antesArroba.contains(" ")) {
            throw new EmailInvalidoException("Digite um email válido !");
        }

        /*
        8. Não pode terminar com .
        9. O . deve estar depois do @
        10. Deve ter caracteres antes do @
        11. Deve ter caracteres depois do @*/

        return true;
    }
}

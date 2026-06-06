package br.com.projetoteatro.service.validators;

public class ValidadorCPF {

    public static boolean isValido(String cpf) {
        int[] digitosCPF = new int[11];
        int contador = 10;
        int soma = 0;

        String cpfSemCaracter = cpf.replace("-","").replace(".","");

        if(cpfSemCaracter.length() != 11) {
            return false;
        }

        for(int i = 0; i < digitosCPF.length; i++) {
            digitosCPF[i] = Character.getNumericValue(cpfSemCaracter.charAt(i));
        }

        for(int i = 0; i < 9; i++) {
            soma += digitosCPF[i] * contador;
            contador--;
        }

        if(!(soma % 11 < 2 && digitosCPF[9] == 0 || soma % 11 >= 2 && digitosCPF[9] == 11 - (soma % 11)))  {
            return false;
        }

        int segundoContador = 11;
        int segundaSoma = 0;

        for(int i = 0; i < 10; i++) {
            segundaSoma += digitosCPF[i] * segundoContador;
            segundoContador--;
        }
        int segundoDigito = (segundaSoma % 11 < 2) ? 0 : 11 - (segundaSoma % 11);

        return digitosCPF[10] == segundoDigito;
    }
}

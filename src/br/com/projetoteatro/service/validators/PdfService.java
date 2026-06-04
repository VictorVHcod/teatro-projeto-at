package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.model.Ingresso;

public class PdfService {

    public void gerarPdfIngresso(Ingresso ingresso) {
        String caminhoArquivo =
                "ingressos/" + ingresso.getCodigo() + ".pdf";

        // continuar dps
        return caminhoArquivo;
    }
}

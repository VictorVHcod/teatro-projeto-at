package br.com.projetoteatro.service;
import br.com.projetoteatro.model.PropostaAluguel;
import br.com.projetoteatro.service.EnviarEmailService;
import br.com.projetoteatro.service.PdfService;

public class ContratoService {

    private PdfService pdfService;
    private EnviarEmailService emailService;

    public void ativarContrato(PropostaAluguel proposta){
        proposta.contratar();

        String pdf =
                pdfService.gerarContrato(proposta);

        emailService.enviarArquivoPdf(
                proposta.getContratante().getEmail(),
                "Contrato de aluguel",
                "Segue contrato em anexo.",
                pdf
        );
    }
}
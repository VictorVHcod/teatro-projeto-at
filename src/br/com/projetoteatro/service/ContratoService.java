package br.com.projetoteatro.service;
import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.Ingresso;
import br.com.projetoteatro.model.PropostaAluguel;
import br.com.projetoteatro.repository.ContratoRepository;
import br.com.projetoteatro.repository.IngressoRepository;

import java.time.LocalDate;
import java.util.List;

public class ContratoService {

    private PdfService pdfService;
    private EnviarEmailService emailService;
    private ContratoRepository contratoRepository = new ContratoRepository();
    private IngressoRepository ingressoRepository = new IngressoRepository();

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
    /*
    private boolean existeIngressoFuturo(long contratoId) {

        List<Ingresso> ingressos = ingressoRepository.listar();

        for (Ingresso i : ingressos) {

            if (i.getSessao().getPropostaAluguel().getId() == contratoId) {

                if (i.getSessao().getData().isAfter(
                        buscarContratoPorId(contratoId).getDataFim())) {

                    return true;
                }
            }
        }

        return false;
    }
    */
    public void encerrarContrato(int contratoId, double valorRepassadoArtista) {

        ContratoRepository contratoRepository = new ContratoRepository();

        PropostaAluguel contrato = contratoRepository.buscaContratoPorId(contratoId);

        // 1. valida se já está ativo
        if (contrato == null) {
            throw new RuntimeException("Contrato não encontrado");
        }

        if (!contrato.getStatusProposta().equals("CONTRATADO")) {
            throw new RuntimeException("Contrato já está encerrado");
        }
        /*
        // 2. regra crítica: não pode ter ingressos futuros
        if (existeIngressoFuturo(contratoId)) {
            throw new RuntimeException(
                    "Não é possível encerrar: existem ingressos vendidos para datas futuras"
            );
        }

        // 3. calcular valores
        double totalVendido = calcularTotalIngressos(contratoId);

        double saldoAluguel = totalVendido - valorRepassadoArtista;

        // 4. atualizar contrato
        contrato.setStatus("ENCERRADO");
        contrato.setDataEncerramento("2026-06-04"); // ideal: LocalDate.now()

        contratoRepository.atualizar(contrato);

        // 5. saída (você pode trocar por PDF/email depois)
        System.out.println("Contrato encerrado com sucesso!");
        System.out.println("Total vendido: " + totalVendido);
        System.out.println("Repassado ao artista: " + valorRepassadoArtista);
        System.out.println("Saldo do aluguel: " + saldoAluguel);

    */
    }
}
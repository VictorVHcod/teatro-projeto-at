package br.com.projetoteatro.service;

import br.com.projetoteatro.model.*;
import br.com.projetoteatro.repository.ContratoRepository;
import br.com.projetoteatro.repository.IngressoRepository;

public class VendaIngressoService {

    private ContratoRepository contratoRepository;
    private IngressoRepository ingressoRepository;

    public VendaIngressoService() {
        contratoRepository = new ContratoRepository();
        ingressoRepository = new IngressoRepository();
    }

    public void venderIngresso(long contratoId,
                               Cliente cliente,
                               Sessao sessao,
                               Assento assento,
                               Setor setor) {

        PropostaAluguel contrato = contratoRepository.buscaContratoPorId(contratoId);

        if (contrato == null) {
            throw new RuntimeException("Espetáculo não encontrado.");
        }

        if (contrato.estaEncerrada()) {
            throw new RuntimeException(
                    "Não é possível vender ingressos para um espetáculo encerrado."
            );
        }

        Ingresso ingresso = new Ingresso(
                cliente,
                sessao,
                assento,
                setor,
                contrato.getValorIngresso()
        );

        ingressoRepository.salvarIngresso(ingresso);
    }
}

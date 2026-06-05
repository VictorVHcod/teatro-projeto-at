package br.com.projetoteatro.service;

import br.com.projetoteatro.model.PropostaAluguel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PdfService {


        public static String gerarContrato(PropostaAluguel proposta) {
            String nomeArquivo ="contrato_" + proposta.getId() + ".pdf";

            try {

                Document documento = new Document();

                PdfWriter.getInstance(
                        documento,
                        new FileOutputStream(nomeArquivo)
                );
                documento.open();

                Font titulo = FontFactory.getFont(
                        FontFactory.HELVETICA_BOLD,
                        18
                );

                Font texto = FontFactory.getFont(
                        FontFactory.HELVETICA,
                        12
                );

                Paragraph cabecalho =
                        new Paragraph(
                                "CONTRATO DE ALUGUEL DO TEATRO",
                                titulo
                        );

                cabecalho.setAlignment(Element.ALIGN_CENTER);
                documento.add(cabecalho);

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(
                        "ID da proposta: "
                                + proposta.getId(),
                        texto
                ));

                documento.add(new Paragraph(
                        "Contratante: "
                                + proposta.getContratante().getNome(),
                        texto
                ));

                documento.add(new Paragraph(
                        "Peça: "
                                + proposta.getNomePeca(),
                        texto
                ));

                documento.add(new Paragraph(
                        "Período: "
                                + proposta.getDataInicio()
                                + " até "
                                + proposta.getDataFim(),
                        texto
                ));

                documento.add(new Paragraph(
                        "Horário: "
                                + proposta.getHorarioInicio()
                                + " às "
                                + proposta.getHorarioFim(),
                        texto
                ));

                documento.add(new Paragraph(
                        "Valor do aluguel: R$ "
                                + proposta.getValorAluguel(),
                        texto
                ));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(
                        "As partes concordam com os termos estabelecidos neste contrato."
                ));

                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(
                        "_________________________________"
                ));

                documento.add(new Paragraph(
                        "Assinatura do Contratante"
                ));

                documento.close();

                return nomeArquivo;

            } catch (Exception e) {
                throw new RuntimeException(
                        "Erro ao gerar PDF.", e
                );
            }
        }
    }


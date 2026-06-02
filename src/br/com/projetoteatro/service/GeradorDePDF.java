package br.com.projetoteatro.service;

import br.com.projetoteatro.model.PropostaAluguel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GeradorDePDF {

    public static void gerarContrato(PropostaAluguel proposta){
        Document doc = new Document(PageSize.A4,50,50,50,50);

        try{
            PdfWriter.getInstance(doc,new FileOutputStream("Proposta_"+ proposta.getId()+ ".pdf"));
            doc.open();
            Font f=new Font(Font.FontFamily.TIMES_ROMAN,12);
            Font t=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
            Paragraph titulo=new Paragraph("PROPOSTA DE ALUGUEL",t);
            titulo.setAlignment(1);
            doc.add(titulo);
            doc.add(new Paragraph("ID: "+proposta.getId(),f));
            doc.add(new Paragraph("Nome Contratante: "+proposta.getContratante().getNome(),f));
            doc.add(new Paragraph("CPF Contratante: "+proposta.getContratante().getCpf(),f));
            doc.add(new Paragraph("Nome Peça: "+proposta.getNomePeca(),f));
            doc.add(new Paragraph("Data início: "+proposta.getDataInicio(),f));
            doc.add(new Paragraph("Data fim: "+proposta.getDataFim(),f));
            doc.add(new Paragraph("Horário: "+proposta.getHorarioInicio()+" até ás "+proposta.getHorarioFim(),f));
            doc.add(new Paragraph("Valor do ingresso: R$ "+proposta.getValorIngresso(),f));
            doc.add(new Paragraph("Valor do contrato: R$ "+proposta.getValorAluguel(),f));
            doc.add(new Paragraph("_________________\nAssinatura Contratante",t));

            doc.close();




        } catch (FileNotFoundException | DocumentException e) {

            e.printStackTrace();
        }

    }
    public static void gerarIngresso(){
        //Bryan adicionar ......o pdf do ingresso, eu acho
    }
}

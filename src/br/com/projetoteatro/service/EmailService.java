package br.com.projetoteatro.service;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailService {
    public static boolean enviarEmail(String destinatario, String assunto, String menssagem,String arquivo, String nomeAnexo) {

        MultiPartEmail email = new MultiPartEmail();

        try {

            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(arquivo);
            anexo.setDescription(EmailAttachment.ATTACHMENT);
            anexo.setName(nomeAnexo);

            //email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication("testandoPoo@gmail.com", "ozlo ijvx xotz htrh");
            email.setSSL(true);
            email.addTo(destinatario); //fiz com meu email para teste
            email.setFrom("testandoPoo@gmail.com");
            email.setSubject(assunto);
            email.setMsg(menssagem);
            //email.attach(anexo);
            email.send();
            System.out.println("Email enviado.....");
            return true;
        } catch (EmailException e) {

            System.out.println("Falha ao enviar email....");
            e.printStackTrace();
            return false;
        }

    }

    public static boolean enviarEmailCodigoSenha(String destinatario, String assunto, String menssagem) {

        MultiPartEmail email = new MultiPartEmail();

        try {

            email.setHostName("smtp.gmail.com");
            email.setAuthentication("testandoPoo@gmail.com", "ozlo ijvx xotz htrh");
            email.setSSL(true);
            email.addTo(destinatario); //fiz com meu email para teste
            email.setFrom("testandoPoo@gmail.com");
            email.setSubject(assunto);
            email.setMsg(menssagem);
            email.send();
            System.out.println("Email enviado.....");
            return true;
        } catch (EmailException e) {

            System.out.println("Falha ao enviar email....");
            e.printStackTrace();
            return false;
        }

    }
}



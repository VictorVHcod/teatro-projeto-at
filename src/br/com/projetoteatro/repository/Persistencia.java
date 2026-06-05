package br.com.projetoteatro.repository;

import br.com.projetoteatro.service.ServicoTeatro;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

public class Persistencia {
    private XStream xstream=new XStream(new DomDriver());

    public void salvarCentral(ServicoTeatro centralInformacoes, String central) {
        String xml=xstream.toXML(centralInformacoes);

        try {

            PrintWriter gravar=new PrintWriter(new File(central));
            gravar.print(xml);
            gravar.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public ServicoTeatro recuperarCentral(String central) {
        File arquivo=new File(central);
        try {if(arquivo.exists()) {
            FileInputStream fis=new FileInputStream(arquivo);
            return (ServicoTeatro)xstream.fromXML(fis);
        }

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ServicoTeatro();
    }

}

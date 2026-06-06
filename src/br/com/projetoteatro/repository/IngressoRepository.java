package br.com.projetoteatro.repository;

import br.com.projetoteatro.model.Ingresso;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IngressoRepository {
    private File ARQUIVO = new File("ingressos.xml");
    private XStream xstream = new XStream();

    public IngressoRepository() {
        xstream.alias("ingressos", List.class);
        xstream.alias("ingresso", Ingresso.class);
    }

    // SALVAR LISTA
    public void salvar(List<Ingresso> ingressos) {

        try {
            if (!ARQUIVO.exists()) {
                ARQUIVO.createNewFile();
            }

            String xml = xstream.toXML(ingressos);

            PrintWriter writer = new PrintWriter(ARQUIVO);
            writer.print(xml);
            writer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // CARREGAR LISTA
    public List<Ingresso> listar() {

        try {
            if (!ARQUIVO.exists()) {
                return new ArrayList<>();
            }

            String xml = new String(Files.readAllBytes(ARQUIVO.toPath()));

            return (List<Ingresso>) xstream.fromXML(xml);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // SALVAR UM INGRESSO
    public void salvarIngresso(Ingresso ingresso) {

        List<Ingresso> ingressos = listar();

        ingressos.add(ingresso);

        salvar(ingressos);
    }

}

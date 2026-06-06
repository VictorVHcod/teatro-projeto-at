package br.com.projetoteatro.repository;

import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.PropostaAluguel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ContratoRepository {
    private XStream xstream = new XStream(new StaxDriver());
    private final File ARQUIVO = new File("contratos.xml");

    public ContratoRepository() {
        // Permite ler os objetos sem dar erro de segurança
        this.xstream.addPermission(AnyTypePermission.ANY);

        // Deixa o XML com a tag <proposta> limpa em vez de br.com.projetoteatro...
        this.xstream.alias("proposta", PropostaAluguel.class);
    }

    public void salvarContrato(PropostaAluguel proposta) {
        List<PropostaAluguel> propostas = carregarContratos();
        propostas.add(proposta);
        String xml = xstream.toXML(propostas);

        try {
            if(!ARQUIVO.exists()) {
                ARQUIVO.createNewFile();
            }

            try (PrintWriter gravar = new PrintWriter(ARQUIVO)) {
                gravar.print(xml);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PropostaAluguel> carregarContratos() {
        try {
            if (!ARQUIVO.exists() || ARQUIVO.length() == 0) {
                return new ArrayList<>();
            }

            String xml = new String(Files.readAllBytes(ARQUIVO.toPath()));

            // Lê o objeto genérico do XML
            Object objetoLido = xstream.fromXML(xml);

            // Se o que estiver no XML for uma Lista, faz o cast direto
            if (objetoLido instanceof List) {
                return (List<PropostaAluguel>) objetoLido;
            }
            // Se for um objeto solo (antigo), cria uma lista nova, adiciona ele e retorna
            else if (objetoLido instanceof PropostaAluguel) {
                List<PropostaAluguel> listaTratada = new ArrayList<>();
                listaTratada.add((PropostaAluguel) objetoLido);
                return listaTratada;
            }

            return new ArrayList<>();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PropostaAluguel buscaContratoPorId(long id) {
        List<PropostaAluguel> propostas = carregarContratos();

        if (propostas == null || propostas.isEmpty()) {
            return null;
        }

        for (PropostaAluguel proposta : propostas) {

            if (proposta.getId() == id) {
                return proposta;
            }
        }

        return null;
    }

    public List<PropostaAluguel> listarTodos() {
        return carregarContratos();
    }
}

package br.com.projetoteatro.repository;

import br.com.projetoteatro.model.Administrador;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorRepository {

    private static final String CAMINHO_ARQUIVO = "administradores.xml";

    public boolean buscaLogin(String usuario, String senha) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);

            // Se o arquivo XML nem existir ainda, ninguém consegue logar
            if (!arquivo.exists()) {
                System.out.println("Arquivo XML não encontrado em: " + arquivo.getAbsolutePath());
                return false;
            }

            // Inicializa os leitores de XML do Java
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(arquivo);

            // Pega a lista de todas as tags <administrador> do arquivo
            NodeList lista = doc.getElementsByTagName("administrador");

            // Percorre cada administrador do XML procurando os dados certos
            for (int i = 0; i < lista.getLength(); i++) {
                Node no = lista.item(i);

                if (no.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) no;

                    String xmlEmail = elemento.getElementsByTagName("email").item(0).getTextContent();
                    String xmlSenha = elemento.getElementsByTagName("senha").item(0).getTextContent();

                    // Se o email e a senha baterem com a tela, retorna true
                    if (xmlEmail.equalsIgnoreCase(usuario) && xmlSenha.equals(senha)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo XML de login: " + e.getMessage());
        }
        // Se percorrer tudo e não achar nada, barra o login
        return false;
    }

    public void cadastrarNovoAdmin(String email, String senha) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;

            // 1. Se o arquivo não existir, cria a estrutura do zero com a tag <administradores>
            if (!arquivo.exists()) {
                doc = builder.newDocument();
                Element raiz = doc.createElement("administradores");
                doc.appendChild(raiz);
            } else {
                // Se já existir, carrega o arquivo atual
                doc = builder.parse(arquivo);
            }

            Element raiz = doc.getDocumentElement();

            // 2. Cria a estrutura do novo administrador: <administrador>
            Element novoAdmin = doc.createElement("administrador");

            // Tag <email>
            Element tagEmail = doc.createElement("email");
            tagEmail.appendChild(doc.createTextNode(email));
            novoAdmin.appendChild(tagEmail);

            // Tag <senha>
            Element tagSenha = doc.createElement("senha");
            tagSenha.appendChild(doc.createTextNode(senha));
            novoAdmin.appendChild(tagSenha);

            // Adiciona o novo bloco dentro da raiz <administradores>
            raiz.appendChild(novoAdmin);

            // 3. Salva as alterações de volta no arquivo físico de forma organizada
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(arquivo);
            transformer.transform(source, result);

            System.out.println("Usuário " + email + " cadastrado com sucesso no XML!");

        } catch (Exception e) {
            System.err.println("Erro ao salvar o novo usuário no XML: " + e.getMessage());
        }
    }
}

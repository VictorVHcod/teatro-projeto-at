package br.com.projetoteatro.view;

import br.com.projetoteatro.model.PropostaAluguel;
import br.com.projetoteatro.repository.ContratoRepository;
import br.com.projetoteatro.repository.IngressoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashBoardVendasView extends JPanel {
    // Componentes dos Cards
    private JLabel lblFaturamento;
    private JLabel lblIngressosHoje;
    private JLabel lblPecaDestaque;

    // Componentes da Tabela
    private JTable tabelaEspetaculos;
    private DefaultTableModel modeloTabela;

    // Repositories para buscar os dados dos XMLs
    private ContratoRepository contratoRepo;
    private IngressoRepository ingressoRepo;

    public DashBoardVendasView() {
        // Inicializa os repositórios (garanta que o XStream atualizado está neles)
        contratoRepo = new ContratoRepository();
        ingressoRepo = new IngressoRepository();

        // Configuração do Painel Principal do Dashboard
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 240, 240)); // Fundo cinza claro padrão

        // 1. Construir e Adicionar a Região dos Cards (Topo)
        add(criarPainelCards(), BorderLayout.NORTH);

        // 2. Construir e Adicionar a Região da Tabela (Centro)
        add(criarPainelTabela(), BorderLayout.CENTER);

        // 3. Carregar os dados vindos dos arquivos XML
        atualizarDadosDashboard();
    }

    private JPanel criarPainelCards() {
        JPanel painelCards = new JPanel(new GridLayout(1, 3, 15, 0));
        painelCards.setOpaque(false); // Mantém o fundo do painel pai

        // Card 1: Faturamento (Verde)
        JPanel cardFaturamento = criarCardEstilizado(" FATURAMENTO BRUTO (MÊS)", new Color(46, 204, 113));
        lblFaturamento = (JLabel) cardFaturamento.getComponent(1);

        // Card 2: Ingressos (Azul)
        JPanel cardIngressos = criarCardEstilizado("INGRESSOS VENDIDOS HOJE", new Color(52, 152, 219));
        lblIngressosHoje = (JLabel) cardIngressos.getComponent(1);

        // Card 3: Destaque (Laranja)
        JPanel cardDestaque = criarCardEstilizado("ESPETÁCULO EM DESTAQUE", new Color(230, 126, 34));
        lblPecaDestaque = (JLabel) cardDestaque.getComponent(1);

        painelCards.add(cardFaturamento);
        painelCards.add(cardIngressos);
        painelCards.add(cardDestaque);

        return painelCards;
    }

    private JPanel criarCardEstilizado(String titulo, Color corFundo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(corFundo);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lblValor = new JLabel("Carregando...");
        lblValor.setForeground(Color.WHITE);
        lblValor.setFont(new Font("Arial", Font.BOLD, 22));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);

        return card;
    }

    private JPanel criarPainelTabela() {
        JPanel painelTabela = new JPanel(new BorderLayout(5, 5));
        painelTabela.setOpaque(false);

        JLabel lblTituloTabela = new JLabel("📊 Próximas Apresentações Agendadas");
        lblTituloTabela.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloTabela.setForeground(new Color(44, 62, 80));
        painelTabela.add(lblTituloTabela, BorderLayout.NORTH);

        // Configura as colunas da JTable
        String[] colunas = {"ID", "Espetáculo / Peça", "Data", "Status do Contrato"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede o usuário de editar o texto da tabela
            }
        };

        tabelaEspetaculos = new JTable(modeloTabela);
        tabelaEspetaculos.setRowHeight(25); // Linhas mais gordinhas e elegantes

        JScrollPane scrollPane = new JScrollPane(tabelaEspetaculos);
        painelTabela.add(scrollPane, BorderLayout.CENTER);

        return painelTabela;
    }

    public void atualizarDadosDashboard() {
        try {
            //Atualiza os Cards com dados fictícios por enquanto (regras matemáticas entram aqui depois)
            lblFaturamento.setText("R$ 00");
            lblIngressosHoje.setText("0 Unidades");
            lblPecaDestaque.setText("Nenhum");

            //Limpa a tabela antes de carregar
            modeloTabela.setRowCount(0);

            //Puxa os dados reais do seu XML usando o seu listarTodos()
            List<PropostaAluguel> contratos = contratoRepo.listarTodos();

            if (contratos != null && !contratos.isEmpty()) {
                for (PropostaAluguel c : contratos) {
                    // Só mostra na tabela do Dashboard peças que não estão encerradas
                    if (!"ENCERRADO".equals(c.getStatusProposta())) {
                        modeloTabela.addRow(new Object[]{
                                c.getId(),
                                c.getNomePeca(),
                                c.getDataInicio() != null ? c.getDataInicio().toString() : "Sem Data", // Certifique-se de que o objeto data converte bem
                                c.getStatusProposta()
                        });
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao atualizar dados do Dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package br.com.projetoteatro.view;

import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.PropostaAluguel;
import br.com.projetoteatro.repository.ContratoRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class GerenciarEspetaculosView extends JPanel {

    private CardLayout internoCardLayout;
    private JPanel painelConteudoInterno;

    public GerenciarEspetaculosView() {
        // 1. Define o layout da própria classe (GerenciarEspetaculosView)
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE); // Garante que não vai ficar cinza padrão

        // 2. Inicializa o CardLayout e o painel contêiner interno
        this.internoCardLayout = new CardLayout();
        this.painelConteudoInterno = new JPanel(this.internoCardLayout);
        this.painelConteudoInterno.setBackground(Color.WHITE);

        // 3. Cria as telas internas (Menus e Formulários)
        JPanel painelSubmenu = criarPainelSubmenu();
        JPanel painelFormulario = criarPainelFormulario();

        // 4. Adiciona as telas ao CardLayout interno com suas respectivas chaves
        this.painelConteudoInterno.add(painelSubmenu, "submenu");
        this.painelConteudoInterno.add(painelFormulario, "formulario");

        // 5. Adiciona o contêiner de cartões no centro deste JPanel principal
        this.add(this.painelConteudoInterno, BorderLayout.CENTER);
    }

    // TELA 1: Submenu do Meio da Tela com os 2 Botões Grandes
    private JPanel criarPainelSubmenu() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.WHITE); // Fundo branco para destacar do resto do sistema

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espaçamento entre os botões

        JButton btnCadastrar = new JButton("Cadastrar Espetáculo (Aluguel)");
        JButton btnListar = new JButton("Visualizar Propostas");

        // Define um tamanho grande e visível para os botões do meio
        Dimension tamBotaoSub = new Dimension(260, 65);
        btnCadastrar.setPreferredSize(tamBotaoSub);
        btnListar.setPreferredSize(tamBotaoSub);

        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnListar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // AÇÃO: Quando clicar em "Cadastrar Espetáculo", o card interno muda para o formulário
        btnCadastrar.addActionListener(e -> {
            internoCardLayout.show(painelConteudoInterno, "formulario");
            painelConteudoInterno.revalidate();
            painelConteudoInterno.repaint();
        });

        // Posiciona o botão Cadastrar na coluna 0, linha 0
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(btnCadastrar, gbc);

        // Posiciona o botão Listar na coluna 1, linha 0
        gbc.gridx = 1; gbc.gridy = 0;
        painel.add(btnListar, gbc);

        return painel;
    }

    // TELA 2: Formulário com os campos baseados na classe PropostaAluguel
    private JPanel criarPainelFormulario() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.WHITE);

        // Título Superior do Formulário
        JLabel lblTitulo = new JLabel("Nova Proposta de Aluguel / Espetáculo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel Central onde vão ficar os campos de texto
        JPanel painelCampos = new JPanel(new GridBagLayout());
        painelCampos.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Criando as caixas de texto para entrada de dados
        JTextField txtNomePeca = new JTextField(20);
        JTextField txtContratante = new JTextField(20);
        JTextField txtValorAluguel = new JTextField(10);
        JTextField txtValorIngresso = new JTextField(10);
        JTextField txtDataInicio = new JTextField(10);
        JTextField txtDataFim = new JTextField(10);
        JTextField txtHoraInicio = new JTextField(8);
        JTextField txtHoraFim = new JTextField(8);

        // Adicionando linha por linha no GridBagLayout (Linhas de 0 a 7)
        adicionarLinhaFormulario(painelCampos, "Nome da Peça / Evento:", txtNomePeca, gbc, 0);
        adicionarLinhaFormulario(painelCampos, "Contratante Responsável:", txtContratante, gbc, 1);
        adicionarLinhaFormulario(painelCampos, "Valor do Aluguel cobrado (R$):", txtValorAluguel, gbc, 2);
        adicionarLinhaFormulario(painelCampos, "Preço base do Ingresso (R$):", txtValorIngresso, gbc, 3);
        adicionarLinhaFormulario(painelCampos, "Data de Início (AAAA-MM-DD):", txtDataInicio, gbc, 4);
        adicionarLinhaFormulario(painelCampos, "Data de Término (AAAA-MM-DD):", txtDataFim, gbc, 5);
        adicionarLinhaFormulario(painelCampos, "Horário de Abertura (HH:MM):", txtHoraInicio, gbc, 6);
        adicionarLinhaFormulario(painelCampos, "Horário de Fechamento (HH:MM):", txtHoraFim, gbc, 7);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // Rodapé com os botões de Ação do formulário
        JPanel painelBotoesAcao = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        painelBotoesAcao.setBackground(Color.WHITE);

        JButton btnSalvar = new JButton("Salvar Proposta");
        JButton btnVoltar = new JButton("Voltar ao Menu");

        // AÇÃO: O botão voltar faz o card interno retornar para o submenu inicial
        btnVoltar.addActionListener(e -> {
            internoCardLayout.show(painelConteudoInterno, "submenu");
            painelConteudoInterno.revalidate();
            painelConteudoInterno.repaint();
        });

        btnSalvar.addActionListener(e -> {
            ContratoRepository contratoRepository = new ContratoRepository();

            try {
                String nomeContratante = txtContratante.getText();
                String nomePeca        = txtNomePeca.getText();
                String textoAluguel    = txtValorAluguel.getText();
                String textoIngresso   = txtValorIngresso.getText();
                String textoDataInic   = txtDataInicio.getText(); // formato: "2026-06-05"
                String textoDataFim    = txtDataFim.getText();
                String textoHoraInic   = txtHoraInicio.getText(); // formato: "20:00"
                String textoHoraFim    = txtHoraFim.getText();

                // 2. CONVERSÃO: Transformando String nos tipos reais que a PropostaAluguel exige
                Contratante contratante = new Contratante(nomeContratante);
                double valorAluguel = Double.parseDouble(textoAluguel);
                double valorIngresso = Double.parseDouble(textoIngresso);

                LocalDate dataInicio = LocalDate.parse(textoDataInic);
                LocalDate dataFim    = LocalDate.parse(textoDataFim);

                LocalTime horaInicio = LocalTime.parse(textoHoraInic);
                LocalTime horaFim    = LocalTime.parse(textoHoraFim);

                PropostaAluguel proposta = new PropostaAluguel(contratante,nomePeca,valorAluguel,dataInicio,dataFim,horaInicio,horaFim,valorIngresso);
                contratoRepository.salvarContrato(proposta);
                JOptionPane.showMessageDialog(this, "Sucesso! Processando dados da peça: " + txtNomePeca.getText());


            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, " Por favor, preencha os campos de valores corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        painelBotoesAcao.add(btnSalvar);
        painelBotoesAcao.add(btnVoltar);
        painelPrincipal.add(painelBotoesAcao, BorderLayout.SOUTH);

        return painelPrincipal;
    }

    // Método facilitador para renderizar as linhas do formulário sem repetir código chato
    private void adicionarLinhaFormulario(JPanel painel, String textoLabel, JComponent campoTexto, GridBagConstraints gbc, int linha) {
        gbc.gridy = linha;

        // Coluna 0: A etiqueta de texto (Label)
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        JLabel label = new JLabel(textoLabel);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        painel.add(label, gbc);

        // Coluna 1: O campo editável (TextField)
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        painel.add(campoTexto, gbc);
    }
}

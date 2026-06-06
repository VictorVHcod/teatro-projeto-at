package br.com.projetoteatro.view;

import br.com.projetoteatro.model.*;
import br.com.projetoteatro.service.VendaIngressoService;
import br.com.projetoteatro.repository.ContratoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BilheteriaView extends JPanel {

    private JComboBox<PropostaAluguel> cbEspetaculos;
    private JComboBox<Cliente> cbClientes;
    private JComboBox<Sessao> cbSessoes;
    private JComboBox<Setor> cbSetores;
    private JTextField txtAssento;
    private JButton btnVender;

    // Construtor
    public BilheteriaView() {
        // Define o layout da tela (Organização em Linhas e Colunas)
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        inicializarComponentes();
        carregarDadosIniciais();
        configurarEventos();
    }

    private void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Combo de Espetáculos
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Selecione o Espetáculo:"), gbc);
        cbEspetaculos = new JComboBox<>();
        gbc.gridx = 1;
        add(cbEspetaculos, gbc);

        // 2. Combo de Clientes
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Selecione o Cliente:"), gbc);
        cbClientes = new JComboBox<>();
        gbc.gridx = 1;
        add(cbClientes, gbc);

        // 3. Combo de Sessões (Ex: "19:00", "21:30")
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Sessão:"), gbc);
        cbSessoes = new JComboBox<>();
        gbc.gridx = 1;
        add(cbSessoes, gbc);

        // 4. Combo de Setores (Ex: "Plataria VIP", "Balcão Nobre")
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Setor:"), gbc);
        cbSetores = new JComboBox<>();
        gbc.gridx = 1;
        add(cbSetores, gbc);

        // 5. Campo do Assento (Ex: "A-12")
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Número do Assento:"), gbc);
        txtAssento = new JTextField(10);
        gbc.gridx = 1;
        add(txtAssento, gbc);

        // 6. Botão Vender
        btnVender = new JButton("Confirmar Venda 🎟️");
        btnVender.setBackground(new Color(46, 204, 113)); // Verde bonito
        btnVender.setForeground(Color.WHITE);
        btnVender.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupa as duas colunas
        add(btnVender, gbc);
    }

    private void carregarDadosIniciais() {
        try {
            // Aqui você vai buscar as listas do banco/XML para preencher os Combos
            ContratoRepository contratoRepo = new ContratoRepository();
            //List<PropostaAluguel> contratosSalvos = contratoRepo.listarTodos(); //  para listar do XML

           // for (PropostaAluguel contrato : contratosSalvos) {
             //   cbEspetaculos.addItem(contrato);
           // }

            // Faça o mesmo para Clientes, Sessoes e Setores...
            // cbClientes.addItem(new Cliente("João da Silva")); // Exemplo temporário

        } catch (Exception e) {
            System.out.println("Aviso: Inicializando com listas vazias por enquanto.");
        }
    }

    private void configurarEventos() {
        btnVender.addActionListener(e -> {
            try {
                // Instancia o serviço que você criou
                VendaIngressoService vendaService = new VendaIngressoService();

                // Captura os objetos selecionados nos Combos da tela
                PropostaAluguel contrato = (PropostaAluguel) cbEspetaculos.getSelectedItem();
                Cliente cliente = (Cliente) cbClientes.getSelectedItem();
                Sessao sessao = (Sessao) cbSessoes.getSelectedItem();
                Setor setor = (Setor) cbSetores.getSelectedItem();

                String numeroAssento = txtAssento.getText().trim();

                // Validação visual rápida
                if (contrato == null || cliente == null || numeroAssento.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de vender!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Assento assento = new Assento(numeroAssento);

                // Dispara a lógica de negócio do seu Service!
                vendaService.venderIngresso(contrato.getId(), cliente, sessao, assento, setor);

                //JOptionPane.showMessageDialog(this, "Ingresso emitido com sucesso para: " + cliente.getNome());
                txtAssento.setText("");

            } catch (RuntimeException ex) {
                // Captura as exceções de negócio lançadas pelo seu Service (Ex: espetáculo encerrado)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro na Venda", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}

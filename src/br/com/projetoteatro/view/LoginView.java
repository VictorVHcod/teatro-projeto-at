package br.com.projetoteatro.view;

import br.com.projetoteatro.repository.AdministradorRepository;

import javax.swing.*;
import java.sql.SQLException;

public class LoginView extends JFrame {
    AdministradorRepository adm = new AdministradorRepository();

    public LoginView() throws SQLException {
        setTitle("Gerenciamento Teatro");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(null);

        JLabel texto = new JLabel("Login Sistema");
        texto.setBounds(150,30,100,20);
        add(texto);

        JLabel lblUser = new JLabel("User: ");
        lblUser.setBounds(50,80,80,25);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(110,80,150,25);
        add(txtUser);

        JLabel lblSenha = new JLabel("Senha: ");
        lblSenha.setBounds(50,130,80,25);
        add(lblSenha);

        JTextField txtSenha = new JTextField();
        txtSenha.setBounds(110,130,150,25);
        add(txtSenha);

        JButton btnLogin = new JButton("login");
        btnLogin.setBounds(150,200,70,30);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            try {
                String usuario = txtUser.getText();
                String senha = txtSenha.getText();

                if(adm.buscaLogin(usuario,senha)) {
                    new DashBoardView().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,"Login inválido");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public static void main(String[] args) throws SQLException {

        new LoginView().setVisible(true);
    }
}

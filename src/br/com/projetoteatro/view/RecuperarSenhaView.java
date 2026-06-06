package br.com.projetoteatro.view;

import javax.swing.*;

public class RecuperarSenhaView extends JFrame {

    public RecuperarSenhaView() {
        setTitle("Recuperar Senha");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel textoSenha = new JLabel("Trocar Senha");
        textoSenha.setHorizontalAlignment(SwingConstants.CENTER);
        textoSenha.setBounds(50,30,300,30);
        add(textoSenha);

        JLabel textoEmail = new JLabel("Email: ");
        textoEmail.setBounds(50,90,300,30);
        add(textoEmail);

        JTextField campoSenha = new JTextField();
        campoSenha.setBounds(100,90,200,30);
        add(campoSenha);


    }
}

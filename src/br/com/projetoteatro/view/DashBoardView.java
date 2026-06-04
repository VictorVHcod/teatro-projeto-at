package br.com.projetoteatro.view;

import javax.swing.*;

public class DashBoardView extends JFrame {

    public DashBoardView() {
        setTitle("Gerenciamento Teatro");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(null);

        JLabel lblAdm = new JLabel("Adminitrativo: ");
        lblAdm.setBounds(80,150,100,100);
        add(lblAdm);

        JButton btnAdm = new JButton();
        btnAdm.setBounds(80,110,100,100);
        add(btnAdm);
    }
}

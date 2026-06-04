package br.com.projetoteatro.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBanco {
    private static final String URL = "jdbc:sqlite:src/br/com/projetoteatro/repository/teatro.db";

    private Connection conexao;

    public ConexaoBanco() throws SQLException {
        conexao = DriverManager.getConnection(URL);
    }

    public Connection getConnection() {
        return conexao;
    }
}

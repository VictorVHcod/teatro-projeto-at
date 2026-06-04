package br.com.projetoteatro.repository;

import br.com.projetoteatro.model.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorRepository {

    public void salvar(Administrador administrador) throws SQLException {
        ConexaoBanco banco = new ConexaoBanco();
        Connection conexao = banco.getConnection();

        String sql = "INSERT INTO administrador(email, senha) VALUES (?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1,administrador.getEmail());
        stmt.setString(2,administrador.getSenha());


        stmt.executeUpdate();
        System.out.println("Administrador salvo com sucesso!");
    }

    public boolean buscaLogin(String usuario, String senha) throws SQLException {
        ConexaoBanco banco = new ConexaoBanco();
        Connection conexao = banco.getConnection();

        String sql = """
                SELECT *
                FROM administrador
                WHERE email = ? AND senha = ?
                """;

        PreparedStatement stmt = conexao.prepareStatement(sql);

        stmt.setString(1,usuario);
        stmt.setString(2,senha);

        ResultSet resultado = stmt.executeQuery();

        if(resultado.next()) {
            return true;
        }
        return false;
    }
}

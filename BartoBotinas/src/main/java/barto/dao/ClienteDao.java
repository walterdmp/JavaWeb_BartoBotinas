package barto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import barto.model.Cliente;

public class ClienteDao {
    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, endereco_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conn = null;

        try {
            conn = MySqlConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, cliente.getNome());
            pStatement.setString(2, cliente.getCpf());
            pStatement.setString(3, cliente.getTelefone());
            pStatement.setString(4, cliente.getEmail());
            pStatement.setInt(5, cliente.getEndereco().getIdEndereco());

            pStatement.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao executar a inserção: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e2) {
                System.out.println("Erro ao fechar o PreparedStatement: " + e2.getMessage());
                e2.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e2) {
                System.out.println("Erro ao fechar a conexão: " + e2.getMessage());
                e2.printStackTrace();
            }
        }
    }
}

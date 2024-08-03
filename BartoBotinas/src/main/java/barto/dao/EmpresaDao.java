package barto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import barto.model.Empresa;

public class EmpresaDao {
    public void cadastrarEmpresa(Empresa empresa) {
        String sql = "INSERT INTO empresa (nome, cnpj, telefone, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conn = null;

        try {
            conn = MySqlConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, empresa.getNome());
            pStatement.setString(2, empresa.getCnpj());
            pStatement.setString(3, empresa.getTelefone());
            pStatement.setString(4, empresa.getEmail());

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

package barto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import barto.model.Fornecedor;

public class FornecedorDao {
	public void cadastrarFornecedor(Fornecedor fornecedor) {
		String sql = "INSERT INTO fornecedor VALUES (null,?,?,?,?)";
		PreparedStatement pStatement = null;
		Connection conn = null;

		try {
			conn = MySqlConnection.getConnection();
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, fornecedor.getNome());
			pStatement.setString(2, fornecedor.getCnpj());
			pStatement.setString(3, fornecedor.getTelefone());
			pStatement.setString(4, fornecedor.getEmail());

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

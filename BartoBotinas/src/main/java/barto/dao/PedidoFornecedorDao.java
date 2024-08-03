package barto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import barto.model.PedidoFornecedor;

public class PedidoFornecedorDao {
    public void cadastrarPedidoFornecedor(PedidoFornecedor pedidoFornecedor) {
        String sql = "INSERT INTO pedido_fornecedor (id_fornecedor, data_pedido, id_produto, quantidade) VALUES (?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conn = null;

        try {
            conn = MySqlConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, pedidoFornecedor.getFornecedor().getIdFornecedor());
            pStatement.setDate(2, Date.valueOf(pedidoFornecedor.getDataPedido()));
            pStatement.setInt(3, pedidoFornecedor.getProduto().getIdProduto());
            pStatement.setInt(4, pedidoFornecedor.getQuantidade());

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

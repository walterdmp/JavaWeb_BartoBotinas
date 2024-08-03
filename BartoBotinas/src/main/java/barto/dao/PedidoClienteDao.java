package barto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import barto.model.PedidoCliente;

public class PedidoClienteDao {
    public void cadastrarPedidoCliente(PedidoCliente pedidoCliente) {
        String sql = "INSERT INTO pedido_cliente (id_cliente, data_pedido, id_produto, quantidade, total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conn = null;

        try {
            conn = MySqlConnection.getConnection();
            pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, pedidoCliente.getCliente().getIdCliente());
            pStatement.setDate(2, Date.valueOf(pedidoCliente.getDataPedido()));
            pStatement.setInt(3, pedidoCliente.getProduto().getIdProduto());
            pStatement.setInt(4, pedidoCliente.getQuantidade());
            pStatement.setDouble(5, pedidoCliente.getTotal());

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

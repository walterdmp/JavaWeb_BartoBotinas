package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.PedidoCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoClienteDao extends GenericoDao<PedidoCliente> {

    public void salvar(PedidoCliente pedido) {
        String insert = "INSERT INTO PEDIDOCLIENTE (idCliente, DATAPEDIDO, idProduto, QUANTIDADE, TOTAL) VALUES (?, ?, ?, ?, ?)";
        save(insert, pedido.getClientePedidoCliente().getIdCliente(), pedido.getDataPedido(), pedido.getProdutoPedidoCliente().getIdProduto(), pedido.getQuantidade(), pedido.getTotal());
    }

    public void alterar(PedidoCliente pedido) {
        String update = "UPDATE PEDIDOCLIENTE SET idCliente=?, DATAPEDIDO=?, idProduto=?, QUANTIDADE=?, TOTAL=? WHERE idPedidoCliente=?";
        save(update, pedido.getClientePedidoCliente().getIdCliente(), pedido.getDataPedido(), pedido.getProdutoPedidoCliente().getIdProduto(), pedido.getQuantidade(), pedido.getTotal(), pedido.getIdPedidoCliente());
    }

    public void excluir(PedidoCliente pedido) {
        String delete = "DELETE FROM PEDIDOCLIENTE WHERE idPedidoCliente=?";
        save(delete, pedido.getIdPedidoCliente());
    }

    public PedidoCliente buscarPorId(int id) {
        String select = "SELECT * FROM PEDIDOCLIENTE WHERE idPedidoCliente=?";
        return buscarPorId(select, new PedidoClienteRowMapper(), id);
    }

    public List<PedidoCliente> buscarTodos() {
        String select = "SELECT * FROM PEDIDOCLIENTE";
        return buscarTodos(select, new PedidoClienteRowMapper());
    }

    public static class PedidoClienteRowMapper implements RowMapper<PedidoCliente> {

        @Override
        public PedidoCliente mapRow(ResultSet rs) throws SQLException {
            PedidoCliente pedido = new PedidoCliente();
            ClienteDao clienteDao = new ClienteDao();
            ProdutoDao produtoDao = new ProdutoDao();

            pedido.setIdPedidoCliente(rs.getInt("idPedidoCliente"));
            pedido.setClientePedidoCliente(clienteDao.buscarPorId(rs.getInt("idCliente")));
            pedido.setDataPedido(rs.getDate("DATAPEDIDO"));
            pedido.setProdutoPedidoCliente(produtoDao.buscarPorId(rs.getInt("idProduto")));
            pedido.setQuantidade(rs.getInt("QUANTIDADE"));
            pedido.setTotal(rs.getDouble("TOTAL"));

            return pedido;
        }
    }
}

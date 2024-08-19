package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.PedidoFornecedor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoFornecedorDao extends GenericoDao<PedidoFornecedor> {

    public void salvar(PedidoFornecedor pf) {
        String insert = "INSERT INTO PEDIDOFORNECEDOR (IDFORNECEDOR, DATAPEDIDO, IDPRODUTO, QUANTIDADE) VALUES (?, ?, ?, ?)";
        save(insert, pf.getFornecedorPedidoFornecedor().getIdFornecedor(), pf.getDataPedido(), pf.getProdutoPedidoFornecedor().getIdProduto(), pf.getQuantidade());
    }

    public void alterar(PedidoFornecedor pf) {
        String update = "UPDATE PEDIDOFORNECEDOR SET IDFORNECEDOR=?, DATAPEDIDO=?, IDPRODUTO=?, QUANTIDADE=? WHERE IDPEDIDOFORNECEDOR=?";
        save(update, pf.getFornecedorPedidoFornecedor().getIdFornecedor(), pf.getDataPedido(), pf.getProdutoPedidoFornecedor().getIdProduto(), pf.getQuantidade(), pf.getIdPedidoFornecedor());
    }

    public void excluir(PedidoFornecedor pf) {
        String delete = "DELETE FROM PEDIDOFORNECEDOR WHERE IDPEDIDOFORNECEDOR=?";
        save(delete, pf.getIdPedidoFornecedor());
    }

    public PedidoFornecedor buscarPorId(int id) {
        String select = "SELECT * FROM PEDIDOFORNECEDOR WHERE IDPEDIDOFORNECEDOR=?";
        return buscarPorId(select, new PedidoFornecedorRowMapper(), id);
    }

    public List<PedidoFornecedor> buscarTodos() {
        String select = "SELECT * FROM PEDIDOFORNECEDOR";
        return buscarTodos(select, new PedidoFornecedorRowMapper());
    }

    public static class PedidoFornecedorRowMapper implements RowMapper<PedidoFornecedor> {

        @Override
        public PedidoFornecedor mapRow(ResultSet rs) throws SQLException {
            PedidoFornecedor pedidoFornecedor = new PedidoFornecedor();
            FornecedorDao fornecedorDao = new FornecedorDao();
            ProdutoDao produtoDao = new ProdutoDao();

            pedidoFornecedor.setIdPedidoFornecedor(rs.getInt("IDPEDIDOFORNECEDOR"));
            pedidoFornecedor.setFornecedorPedidoFornecedor(fornecedorDao.buscarPorId(rs.getInt("IDFORNECEDOR")));
            pedidoFornecedor.setDataPedido(rs.getDate("DATAPEDIDO"));
            pedidoFornecedor.setProdutoPedidoFornecedor(produtoDao.buscarPorId(rs.getInt("IDPRODUTO")));
            pedidoFornecedor.setQuantidade(rs.getInt("QUANTIDADE"));

            return pedidoFornecedor;
        }
    }
}

package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDao extends GenericoDao<Produto> {

    public void salvar(Produto p) {
        String insert = "INSERT INTO PRODUTO (DESCRICAO, PRECO, QUANTIDADE) VALUES (?, ?, ?)";
        save(insert, p.getDescricao(), p.getPreco(), p.getQuantidade());
    }

    public void alterar(Produto p) {
        String update = "UPDATE PRODUTO SET DESCRICAO=?, PRECO=?, QUANTIDADE=? WHERE IDPRODUTO=?";
        save(update, p.getDescricao(), p.getPreco(), p.getQuantidade(), p.getIdProduto());
    }

    public void excluir(Produto p) {
        String delete = "DELETE FROM PRODUTO WHERE IDPRODUTO=?";
        save(delete, p.getIdProduto());
    }

    public Produto buscarPorId(int id) {
        String select = "SELECT * FROM PRODUTO WHERE IDPRODUTO=?";
        return buscarPorId(select, new ProdutoRowMapper(), id);
    }

    public List<Produto> buscarTodos() {
        String select = "SELECT * FROM PRODUTO";
        return buscarTodos(select, new ProdutoRowMapper());
    }

    public static class ProdutoRowMapper implements RowMapper<Produto> {

        @Override
        public Produto mapRow(ResultSet rs) throws SQLException {
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("IDPRODUTO"));
            produto.setDescricao(rs.getString("DESCRICAO"));
            produto.setPreco(rs.getFloat("PRECO"));
            produto.setQuantidade(rs.getInt("QUANTIDADE"));
            return produto;
        }
    }
}

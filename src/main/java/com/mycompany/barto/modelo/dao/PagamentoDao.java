package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PagamentoDao extends GenericoDao<Pagamento> {

    public void salvar(Pagamento pagamento) {
        String insert = "INSERT INTO pagamento(dataPagamento, valor, pago, descricao) VALUES (?, ?, ?, ?)";
        save(insert, pagamento.getDataPagamento(), pagamento.getValor(), pagamento.isPago() ? 1 : 0, pagamento.getDescricao());
    }

    public void alterar(Pagamento pagamento) {
        String update = "UPDATE pagamento SET dataPagamento=?, valor=?, pago=?, descricao=? WHERE idPagamento=?";
        save(update, pagamento.getDataPagamento(), pagamento.getValor(), pagamento.isPago() ? 1 : 0, pagamento.getDescricao(), pagamento.getIdPagamento());
    }

    public void excluir(Pagamento pagamento) {
        String delete = "DELETE FROM pagamento WHERE idPagamento=?";
        save(delete, pagamento.getIdPagamento());
    }

    public Pagamento buscarPorId(int id) {
        String select = "SELECT * FROM pagamento WHERE idPagamento=?";
        return buscarPorId(select, new PagamentoRowMapper(), id);
    }

    public List<Pagamento> buscarTodos() {
        String select = "SELECT * FROM pagamento";
        return buscarTodos(select, new PagamentoRowMapper());
    }

    public static class PagamentoRowMapper implements RowMapper<Pagamento> {

        @Override
        public Pagamento mapRow(ResultSet rs) throws SQLException {
            Pagamento pagamento = new Pagamento();
            pagamento.setIdPagamento(rs.getInt("idPagamento"));
            pagamento.setDataPagamento(rs.getDate("dataPagamento"));
            pagamento.setValor(rs.getFloat("valor"));
            pagamento.setPago(rs.getBoolean("pago"));
            pagamento.setDescricao(rs.getString("descricao"));
            return pagamento;
        }
    }
}

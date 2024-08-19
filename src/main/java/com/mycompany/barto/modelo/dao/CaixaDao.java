package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Caixa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CaixaDao extends GenericoDao<Caixa> {

    public void salvar(Caixa caixa) {
        String insert = "INSERT INTO CAIXA(TIPO, VALOR, DESCRICAO, DATA) VALUES (?, ?, ?, ?)";
        save(insert, caixa.getTipo(), caixa.getValor(), caixa.getDescricao(), caixa.getData());
    }

    public void alterar(Caixa caixa) {
        String update = "UPDATE CAIXA SET TIPO=?, VALOR=?, DESCRICAO=?, DATA=? WHERE idCaixa=?";
        save(update, caixa.getTipo(), caixa.getValor(), caixa.getDescricao(), caixa.getData(), caixa.getIdCaixa());
    }

    public void excluir(Caixa caixa) {
        String delete = "DELETE FROM CAIXA WHERE idCaixa=?";
        save(delete, caixa.getIdCaixa());
    }

    public Caixa buscarPorId(int id) {
        String select = "SELECT * FROM CAIXA WHERE idCaixa=?";
        return buscarPorId(select, new CaixaRowMapper(), id);
    }

    public List<Caixa> buscarTodas() {
        String select = "SELECT * FROM CAIXA";
        return buscarTodos(select, new CaixaRowMapper());
    }

    public static class CaixaRowMapper implements RowMapper<Caixa> {

        @Override
        public Caixa mapRow(ResultSet rs) throws SQLException {
            Caixa caixa = new Caixa();
            caixa.setIdCaixa(rs.getInt("idCaixa"));
            caixa.setTipo(rs.getString("TIPO"));
            caixa.setValor(rs.getFloat("VALOR"));
            caixa.setDescricao(rs.getString("DESCRICAO"));
            caixa.setData(rs.getDate("DATA"));

            return caixa;
        }
    }
}

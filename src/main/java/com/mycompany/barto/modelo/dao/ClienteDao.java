package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao extends GenericoDao<Cliente> {

    public void salvar(Cliente cliente) {
        String insert = "INSERT INTO CLIENTE(NOME, CPF, TELEFONE, EMAIL, ENDERECO) VALUES (?, ?, ?, ?, ?)";
        save(insert, cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), cliente.getEndereco());
    }

    public void alterar(Cliente cliente) {
        String update = "UPDATE CLIENTE SET NOME=?, CPF=?, TELEFONE=?, EMAIL=?, ENDERECO=? WHERE idCliente=?";
        save(update, cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(), cliente.getEndereco(), cliente.getIdCliente());
    }

    public void excluir(Cliente cliente) {
        String delete = "DELETE FROM CLIENTE WHERE idCliente=?";
        save(delete, cliente.getIdCliente());
    }

    public Cliente buscarPorId(int id) {
        String select = "SELECT * FROM CLIENTE WHERE idCliente=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }

    public List<Cliente> buscarTodos() {
        String select = "SELECT * FROM CLIENTE";
        return buscarTodos(select, new ClienteRowMapper());
    }

    public static class ClienteRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEmail(rs.getString("EMAIL"));
            cliente.setEndereco(rs.getString("ENDERECO"));

            return cliente;
        }
    }
}

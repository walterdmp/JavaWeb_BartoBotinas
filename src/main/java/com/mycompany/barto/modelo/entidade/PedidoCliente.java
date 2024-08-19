package com.mycompany.barto.modelo.entidade;

import java.util.Date;

public class PedidoCliente {

    private int idPedidoCliente;
    private Date dataPedido;
    private int quantidade;
    private double total;
    private Integer idCliente;
    private Integer idProduto;
    private Cliente clientePedidoCliente = new Cliente();
    private Produto produtoPedidoCliente = new Produto();

    public PedidoCliente(int idPedidoCliente, Date dataPedido, int quantidade, double total, Integer idCliente, Integer idProduto) {
        this.idPedidoCliente = idPedidoCliente;
        this.dataPedido = dataPedido;
        this.quantidade = quantidade;
        this.total = total;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
    }

    public PedidoCliente(Date dataPedido, int quantidade, double total, Integer idCliente, Integer idProduto) {
        this.dataPedido = dataPedido;
        this.quantidade = quantidade;
        this.total = total;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
    }

    public PedidoCliente() {
    }

    public int getIdPedidoCliente() {
        return idPedidoCliente;
    }

    public void setIdPedidoCliente(int idPedidoCliente) {
        this.idPedidoCliente = idPedidoCliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Cliente getClientePedidoCliente() {
        return clientePedidoCliente;
    }

    public void setClientePedidoCliente(Cliente clientePedidoCliente) {
        this.clientePedidoCliente = clientePedidoCliente;
    }

    public Produto getProdutoPedidoCliente() {
        return produtoPedidoCliente;
    }

    public void setProdutoPedidoCliente(Produto produtoPedidoCliente) {
        this.produtoPedidoCliente = produtoPedidoCliente;
    }

}

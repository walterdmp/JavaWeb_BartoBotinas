package com.mycompany.barto.modelo.entidade;

import java.util.Date;

public class PedidoFornecedor {

    private int idPedidoFornecedor;
    private Date dataPedido;
    private int quantidade;
    private Integer idFornecedor;
    private Integer idProduto;
    private Fornecedor fornecedorPedidoFornecedor = new Fornecedor();
    private Produto produtoPedidoFornecedor = new Produto();

    public PedidoFornecedor(int idPedidoFornecedor, Date dataPedido, int quantidade, Integer idFornecedor, Integer idProduto) {
        this.idPedidoFornecedor = idPedidoFornecedor;
        this.dataPedido = dataPedido;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
    }

    public PedidoFornecedor(Date dataPedido, int quantidade, Integer idFornecedor, Integer idProduto) {
        this.dataPedido = dataPedido;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
    }

    public PedidoFornecedor() {
    }

    public int getIdPedidoFornecedor() {
        return idPedidoFornecedor;
    }

    public void setIdPedidoFornecedor(int idPedidoFornecedor) {
        this.idPedidoFornecedor = idPedidoFornecedor;
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

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Fornecedor getFornecedorPedidoFornecedor() {
        return fornecedorPedidoFornecedor;
    }

    public void setFornecedorPedidoFornecedor(Fornecedor fornecedorPedidoFornecedor) {
        this.fornecedorPedidoFornecedor = fornecedorPedidoFornecedor;
    }

    public Produto getProdutoPedidoFornecedor() {
        return produtoPedidoFornecedor;
    }

    public void setProdutoPedidoFornecedor(Produto produtoPedidoFornecedor) {
        this.produtoPedidoFornecedor = produtoPedidoFornecedor;
    }
    
    
    
}

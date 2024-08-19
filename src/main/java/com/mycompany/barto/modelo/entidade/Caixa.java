package com.mycompany.barto.modelo.entidade;

import java.util.Date;

public class Caixa {

    private int idCaixa;
    private String tipo;
    private float valor;
    private String descricao;
    private Date data;

    public Caixa(int idCaixa, String tipo, float valor, String descricao, Date data) {
        this.idCaixa = idCaixa;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    public Caixa(String tipo, float valor, String descricao, Date data) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    public Caixa() {
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}

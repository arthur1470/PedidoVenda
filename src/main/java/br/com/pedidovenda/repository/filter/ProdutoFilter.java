package br.com.pedidovenda.repository.filter;

import br.com.pedidovenda.validation.SKU;

import java.io.Serializable;

public class ProdutoFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    @SKU
    private String sku;
    private String nome;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

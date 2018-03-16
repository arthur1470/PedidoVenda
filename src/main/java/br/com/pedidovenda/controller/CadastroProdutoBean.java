package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Produto;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Produto produto;

    public CadastroProdutoBean() {
        this.produto = new Produto();
    }

    public void salvar(){
        System.out.println(this.produto.getValorUnitario());
    }

    public Produto getProduto() {
        return produto;
    }

}

package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Categorias;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    private Produto produto;

    private List<Categoria> categoriasRaizes;

    @NotNull
    private Categoria categoriaPai;

    public CadastroProdutoBean() {
        this.produto = new Produto();
    }

    public void inicializar(){
        categoriasRaizes = categorias.raizes();
    }

    public void salvar(){
        System.out.println("categoria selecionada " + categoriaPai.getDescricao());
    }

    public Produto getProduto() {
        return produto;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }
}

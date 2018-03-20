package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    private Produto produto;

    private List<Categoria> categoriasRaizes;

    public CadastroProdutoBean() {
        this.produto = new Produto();
    }

    public void inicializar(){
        System.out.println("inicializando");
        categoriasRaizes = manager.createQuery("FROM Categoria").getResultList();
    }

    public void salvar(){

    }

    public Produto getProduto() {
        return produto;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

}

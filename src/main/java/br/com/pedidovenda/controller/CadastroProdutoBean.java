package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.service.CadastroProdutoService;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    @Inject
    private CadastroProdutoService cadastroProdutoService;

    private Produto produto;

    private List<Categoria> categoriasRaizes;
    private List<Categoria> subCategorias;

    @NotNull
    private Categoria categoriaPai;

    public CadastroProdutoBean() {
        limpar();
    }

    public void inicializar(){
        if(FacesUtil.isNotPostback()) {
            categoriasRaizes = categorias.raizes();
        }
    }

    public void carregarSubcategorias(){
        subCategorias = categorias.subcategoriasDe(categoriaPai);
    }

    private void limpar(){
        produto = new Produto();
        categoriaPai = null;
        subCategorias = new ArrayList<>();
    }

    public void salvar(){
        this.produto = cadastroProdutoService.salvar(this.produto);
        limpar();

        FacesUtil.addInfoMessage("Produto salvo com sucesso!");
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

    public List<Categoria> getSubCategorias() {
        return subCategorias;
    }
}

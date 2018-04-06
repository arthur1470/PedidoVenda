package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.repository.filter.ProdutoFilter;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("pesquisaProdutosBean")
@ViewScoped
public class PesquisaProdutosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    private ProdutoFilter filtro;
    private List<Produto> produtosFiltrados;

    private Produto produtoSelecionado;

    public PesquisaProdutosBean() {
        filtro = new ProdutoFilter();
    }

    public void pesquisar() {
        produtosFiltrados = produtos.filtrados(filtro);
    }

    public void remover() {
        produtos.remover(produtoSelecionado);
        produtosFiltrados.remove(produtoSelecionado);
        FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " excluído com sucesso!");
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}
package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.repository.filter.ProdutoFilter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("pesquisaProdutosBean")
@SessionScoped
public class PesquisaProdutosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Produto> produtosFiltrados;

	private ProdutoFilter filtro;

	public PesquisaProdutosBean(){
		filtro = new ProdutoFilter();
	}

	@Inject
	private Produtos produtos;

	public void pesquisar(){
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}
}

package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pesquisaProdutosBean")
@SessionScoped
public class PesquisaProdutosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Integer> produtosFiltrados;

	public PesquisaProdutosBean() {
		this.produtosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			this.produtosFiltrados.add(i);
		}

	}

	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}

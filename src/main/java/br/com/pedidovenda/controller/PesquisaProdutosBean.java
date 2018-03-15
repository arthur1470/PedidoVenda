package br.com.pedidovenda.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("pesquisaProdutosBean")
@SessionScoped
public class PesquisaProdutosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Integer> produtosFiltrados;

	@PostConstruct
	public void init() {
		this.produtosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			this.produtosFiltrados.add(i);
		}

	}

	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}

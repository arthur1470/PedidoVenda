package br.com.pedidovenda.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("cadastroPedidoBean")
@RequestScoped
public class CadastroPedidoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Integer> itens;

	@PostConstruct
	public void init() {
		this.itens = new ArrayList<>();
		
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
}

package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "cadastroPedidoBean")
@RequestScoped
public class CadastroPedidoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Integer> itens;
	
	public CadastroPedidoBean() {
		this.itens = new ArrayList<>();
		
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
}

package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.EnderecoEntrega;
import br.com.pedidovenda.model.FormaPagamento;
import br.com.pedidovenda.model.Pedido;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("cadastroPedidoBean")
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Pedido pedido;

	private List<Integer> itens;

	@PostConstruct
	public void init() {
		this.pedido = new Pedido();
		this.pedido.setEnderecoEntrega(new EnderecoEntrega());
		this.itens = new ArrayList<>();
		itens.add(1);
	}

	public void salvar(){
		System.out.println(this.pedido.getFormaPagamento());
	}

	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public FormaPagamento[] getFormaPagamento(){
		return FormaPagamento.values();
	}
}

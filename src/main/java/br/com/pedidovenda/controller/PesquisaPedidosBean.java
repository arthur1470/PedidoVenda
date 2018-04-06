package br.com.pedidovenda.controller;


import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.repository.filter.PedidoFilter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("pesquisaPedidosBean")
@ViewScoped
public class PesquisaPedidosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filtro;
    private List<Pedido> pedidosFiltrados;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filtro);
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

    public StatusPedido[] getStatuses() {
        return StatusPedido.values();
    }

    public void setFiltro(PedidoFilter filtro) {
        this.filtro = filtro;
    }
}

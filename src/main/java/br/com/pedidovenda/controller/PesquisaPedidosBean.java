package br.com.pedidovenda.controller;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("pesquisaPedidosBean")
@SessionScoped
public class PesquisaPedidosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Integer> pedidosFiltrados;

    @PostConstruct
    public void init() {
        this.pedidosFiltrados = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            this.pedidosFiltrados.add(i);
        }
    }

    public List<Integer> getPedidosFiltrados() {
        return pedidosFiltrados;
    }
}

package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.*;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.service.CadastroPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("cadastroPedidoBean")
@ViewScoped
public class CadastroPedidoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    private Pedido pedido;
    private List<Usuario> vendedores;

    @PostConstruct
    public void init() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            this.vendedores = usuarios.vendedores();
        }
    }

    private void limpar() {
        this.pedido = new Pedido();
        this.pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public void salvar() {
        this.pedido = this.cadastroPedidoService.salvar(this.pedido);

        FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
    }

    public boolean isEditando(){
        return !this.pedido.isNovo();
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public List<Cliente> completarCliente(String nome) {
        return clientes.porNome(nome);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        return vendedores;
    }
}

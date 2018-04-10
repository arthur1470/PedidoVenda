package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.*;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.service.CadastroPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.validation.SKU;
import org.apache.commons.lang3.StringUtils;

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
    private Produtos produtos;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @SKU
    private String sku;

    private Pedido pedido;
    private List<Usuario> vendedores;
    private Produto produtoLinhaEditavel;

    @PostConstruct
    public void init() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            this.vendedores = usuarios.vendedores();

            this.pedido.adicionarItemVazio();

            this.recalcularPedido();
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

    public void recalcularPedido() {
        if (this.pedido != null) {
            this.pedido.recalcularValorTotal();
        }
    }

    public void carregarProdutoPorSku() {
        if (StringUtils.isNotEmpty(this.sku)) {
            this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
            this.carregarProdutoLinhaEditavel();
        }
    }

    public void carregarProdutoLinhaEditavel() {
        ItemPedido item = this.pedido.getItens().get(0);

        if (this.produtoLinhaEditavel != null) {
            if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
                FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
            } else {
                item.setProduto(this.produtoLinhaEditavel);
                item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.sku = null;

                this.pedido.recalcularValorTotal();
            }
        }
    }

    private boolean existeItemComProduto(Produto produtoLinhaEditavel) {
        boolean existeItem = false;

        for (ItemPedido item : this.getPedido().getItens()) {
            if (produtoLinhaEditavel.equals(item.getProduto())) {
                existeItem = true;
                break;
            }
        }

        return existeItem;
    }

    public List<Produto> completarProduto(String nome) {
        return this.produtos.porNome(nome);
    }

    public void atualizarQuantidade(ItemPedido item, int linha) {
        if (item.getQuantidade() < 1) {
            if (linha == 0) {
                item.setQuantidade(1);
            } else {
                this.getPedido().getItens().remove(linha);
            }
        }
        this.pedido.recalcularValorTotal();
    }

    public boolean isEditando() {
        return !this.pedido.isNovo();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }
}

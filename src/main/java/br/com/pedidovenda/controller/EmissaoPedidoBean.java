package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.service.EmissaoPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("emissaoPedidoBean")
@RequestScoped
public class EmissaoPedidoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmissaoPedidoService emissaoPedidoService;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEventEvent;

    public void emitirPedido() {
        this.pedido.removerItemVazio();

        try {
            this.pedido = this.emissaoPedidoService.emitir(this.pedido);
            pedidoAlteradoEventEvent.fire(new PedidoAlteradoEvent(this.pedido));

            FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }
}

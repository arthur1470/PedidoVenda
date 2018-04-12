package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.service.CancelamentoPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("cancelamentoPedidoBean")
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CancelamentoPedidoService cancelamentoPedidoService;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void cancelarPedido() {
        this.pedido = cancelamentoPedidoService.cancelar(this.pedido);
        pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

        FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
    }
}

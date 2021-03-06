package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CancelamentoPedidoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Inject
    private EstoqueService estoqueService;

    @Transactional
    public Pedido cancelar(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());

        if (pedido.isNaoCancelavel()) {
            throw new NegocioException("Pedido não pode ser cancelado no status "
                    + pedido.getStatus().getDescricao()
                    + ".");
        }

        if (pedido.isEmitido()) {
            this.estoqueService.retornarItensEstoque(pedido);
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        pedido = this.pedidos.guardar(pedido);

        return pedido;
    }
}

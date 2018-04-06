package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    private Pedidos pedidos;

    public PedidoConverter() {
        pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Pedido retorno = null;

        if (s != null) {
            Long id = new Long(s);
            retorno = this.pedidos.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if (o != null) {
            Pedido pedido = (Pedido) o;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }

        return "";
    }
}

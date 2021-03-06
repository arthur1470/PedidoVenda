package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    private Clientes clientes;

    public ClienteConverter() {
        clientes = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Cliente retorno = null;

        if (s != null) {
            Long id = new Long(s);
            retorno = clientes.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            return ((Cliente) o).getId().toString();
        }
        return "";
    }
}
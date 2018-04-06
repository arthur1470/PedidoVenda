package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    private Usuarios usuarios;

    public UsuarioConverter() {
        this.usuarios = CDIServiceLocator.getBean(Usuarios.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Usuario retorno = null;

        if (s != null) {
            Long id = new Long(s);
            retorno = usuarios.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            return ((Usuario) o).getId().toString();
        }
        return "";
    }
}

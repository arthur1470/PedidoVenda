package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Categoria retorno = null;
        if(s != null){
            Long id = new Long(s);
            retorno = categorias.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            return ((Categoria) o).getId().toString();
        }
        return null;
    }
}

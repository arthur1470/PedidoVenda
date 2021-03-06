package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    private Produtos produtos;

    public ProdutoConverter(){
         produtos = CDIServiceLocator.getBean(Produtos.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Produto retorno = null;

        if(s != null){
            Long id = new Long(s);
            retorno = this.produtos.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if(o != null){
            Produto produto = (Produto) o;
            return produto.getId() == null ? null : produto.getId().toString();
        }

        return "";
    }
}

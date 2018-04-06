package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Usuario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }

    public List<Usuario> vendedores(){
        return manager.createQuery("FROM Usuario", Usuario.class)
                .getResultList();
    }

}

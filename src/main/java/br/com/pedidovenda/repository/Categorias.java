package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Categoria;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public List<Categoria> raizes(){
        return manager.createQuery("FROM Categoria", Categoria.class).getResultList();
    }

    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }
}

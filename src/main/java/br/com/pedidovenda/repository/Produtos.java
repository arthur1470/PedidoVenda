package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;

public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Produto guardar(Produto produto) {
        return manager.merge(produto);
    }

    public Produto porSku(String sku) {
        try {
            return manager.createQuery("FROM Produto WHERE UPPER(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

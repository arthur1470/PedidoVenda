package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Cliente;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

    public List<Cliente> porNome(String nome) {
        return manager.createQuery("FROM Cliente " +
                "WHERE UPPER(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

}

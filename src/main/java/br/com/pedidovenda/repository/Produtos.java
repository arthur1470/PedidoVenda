package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.filter.ProdutoFilter;
import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Produto guardar(Produto produto) {
        return manager.merge(produto);
    }

    @Transactional
    public void remover(Produto produto) {
        try {
            produto = porId(produto.getId());
            manager.remove(produto);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluído.");
        }
    }

    public Produto porSku(String sku) {
        try {
            return manager.createQuery("FROM Produto WHERE UPPER(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Produto> filtrados(ProdutoFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Produto.class);

        if (StringUtils.isNotBlank(filtro.getSku())) {
            criteria.add(Restrictions.eq("sku", filtro.getSku()));
        }

        // where nome like '%exemplo%'
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Produto porId(Long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> porNome(String nome) {
        return this.manager.createQuery("FROM Produto WHERE UPPER(nome) LIKE :nome ", Produto.class)
                .setParameter("nome", nome.toUpperCase() + "%").getResultList();
    }
}

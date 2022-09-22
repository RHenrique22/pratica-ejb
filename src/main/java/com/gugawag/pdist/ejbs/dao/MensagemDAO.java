package com.gugawag.pdist.ejbs.dao;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MensagemDAO {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public void inserir(Mensagem mensagem) {
        entityManager.persist(mensagem);
    }

    public List<Mensagem> listar() {
        return this.entityManager.createQuery("FROM Mensagem M").getResultList();
    }

    public Mensagem pesquisar(long id) {
        return (Mensagem) this.entityManager.createQuery("FROM Mensagem M WHERE M.id = " + id).getSingleResult();
    }
}

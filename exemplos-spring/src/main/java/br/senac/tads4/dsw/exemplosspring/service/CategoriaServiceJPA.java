/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;

import br.senac.tads4.dsw.exemplosspring.model.Categoria;
import br.senac.tads4.dsw.exemplosspring.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando.tsuda
 */
@Repository
public class CategoriaServiceJPA implements CategoriaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Categoria> findAll() {
        Query queryJPQL
                = entityManager.createNamedQuery("Categoria.findAll");
        List<Categoria> resultados = queryJPQL.getResultList();
        return resultados;
    }

    @Override
    public Categoria findById(int id) {
        Query queryJPQL
                = entityManager.createNamedQuery("Categoria.findById");
        queryJPQL.setParameter("idCat", id);
        Categoria resultado = (Categoria) queryJPQL.getSingleResult();
        return resultado;
    }

}

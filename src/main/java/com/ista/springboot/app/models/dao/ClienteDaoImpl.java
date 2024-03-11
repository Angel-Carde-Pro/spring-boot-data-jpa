/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.springboot.app.models.dao;

import com.ista.springboot.app.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author ANGEL CÁRDENAS
 */
@Repository
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Transactional
    @Override
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }

}

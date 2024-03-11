/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.springboot.app.models.dao;

import com.ista.springboot.app.models.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ANGEL CÁRDENAS
 */
@Repository
public class TicketDaoImpl implements ITicketDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Ticket> findAll() {
        return em.createQuery("from Ticket").getResultList();
    }
    
    @Transactional
    @Override
    public void save(Ticket ticket) {
        if (ticket.getId() != null && ticket.getId() > 0) {
            em.merge(ticket);
        } else {
            em.persist(ticket);
        }
    }
    
    @Override
    public Ticket findOne(Long id) {
        return em.find(Ticket.class, id);
    }
    
    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
    
}

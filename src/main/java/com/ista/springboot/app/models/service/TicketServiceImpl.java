/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.springboot.app.models.service;

import com.ista.springboot.app.models.dao.ITicketDao;
import com.ista.springboot.app.models.entity.Ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PC
 */
@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketDao ticketDao;

    @Transactional(readOnly = true)
    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Transactional
    @Override
    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    @Transactional(readOnly = true)
    @Override
    public Ticket findOne(Long id) {
        return ticketDao.findOne(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        ticketDao.delete(id);
    }

}

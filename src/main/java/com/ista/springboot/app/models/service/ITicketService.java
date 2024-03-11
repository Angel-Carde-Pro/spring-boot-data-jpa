/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ista.springboot.app.models.service;

import com.ista.springboot.app.models.entity.Ticket;

import java.util.List;

/**
 * @author PC
 */
public interface ITicketService {

    public List<Ticket> findAll();

    public void save(Ticket ticket);

    public Ticket findOne(Long id);

    public void delete(Long id);
}

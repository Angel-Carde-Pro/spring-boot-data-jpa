/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.springboot.app.models.service;

import com.ista.springboot.app.models.dao.IClienteDao;
import com.ista.springboot.app.models.entity.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ANGEL CÁRDENAS
 */
@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private IClienteDao clienteDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
       return clienteDao.findAll();
    }

    @Transactional
    @Override
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findOne(Long id) {
    return clienteDao.findOne(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
       clienteDao.delete(id);
    }
    
    
}

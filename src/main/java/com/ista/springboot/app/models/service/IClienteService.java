/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ista.springboot.app.models.service;

import com.ista.springboot.app.models.entity.Cliente;
import java.util.List;

/**
 *
 * @author ANGEL CÁRDENAS
 */
public interface IClienteService {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);
}

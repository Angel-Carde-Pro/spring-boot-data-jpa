/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ista.springboot.app.controllers;

import com.ista.springboot.app.models.entity.Cliente;
import com.ista.springboot.app.models.entity.Ticket;
import com.ista.springboot.app.models.service.IClienteService;
import com.ista.springboot.app.models.service.ITicketService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 *
 */
@Controller
public class ClienteController {

    @Value("${spring.config.location}")
    private String rootPath;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ITicketService ticketService;

    @RequestMapping({"/", "", "/Inicio"})
    public String Inicio() {
        return "inicio";
    }

//     @RequestMapping({"/", "", "/list"})
//    public String Ticket(Model model) {
//             model.addAttribute("titulo","Abrir nuevo Ticket");
//        return "list";
//    }
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String listarT(Model model) {
//        model.addAttribute("titulo", "Listado de ticket");
//        model.addAttribute("ticket", ticketService.findAll());
//        return "list";
//    }

    @RequestMapping(value = "/ticket")
    public String crearT(Map<String, Object> model) {
        Ticket ticket = new Ticket();
        model.put("ticket", ticket);
        model.put("titulo", "Abrir nuevo ticket");
        return "ticket";
    }
@RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public String guardarT(Ticket ticket, RedirectAttributes flash, @RequestParam("file") MultipartFile foto) {
        if (!foto.isEmpty()) {

            //  Path directorioFotos = Paths.get("src//main//resources//static/uploads");
            //  String rootPath = directorioFotos.toFile().getAbsolutePath();
            try {
                byte[] bytes = foto.getBytes();

                Path Rutaarchivo = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(Rutaarchivo, bytes);
                flash.addFlashAttribute("info", "Se ha subido correctamente la foto del ticket" );
                ticket.setFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String mensajeFls = (ticket.getId() != null) ? "El registro de ticket se a editado con éxito" : "El registro de ticket se a creado con exito";
        ticketService.save(ticket);
        flash.addFlashAttribute("success", mensajeFls);
        return "redirect:list";
    }
     @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listarT(Model model) {
        model.addAttribute("titulo", "Listado de tickets");
        model.addAttribute("tickets", ticketService.findAll());
        return "list";
    }
    
    @GetMapping(value = "/visualizar/{id}")
    public String verT(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Ticket ticket = ticketService.findOne(id);
        if (ticket == null) {
            flash.addFlashAttribute("error", "El ticket no se encuentra en la base de datos");
            return "redirect:/list";
        }
        model.put("ticket", ticket);
        model.put("titulo", "Informacion del ticket" + ticket.getAsunto());
        return "visualizar";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no se encuentra en la base de datos");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Informacion del cliente" + cliente.getNombre());
        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario del cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(Cliente cliente, RedirectAttributes flash, @RequestParam("file") MultipartFile foto) {
        if (!foto.isEmpty()) {

            //  Path directorioFotos = Paths.get("src//main//resources//static/uploads");
            //  String rootPath = directorioFotos.toFile().getAbsolutePath();
            try {
                byte[] bytes = foto.getBytes();

                Path Rutaarchivo = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(Rutaarchivo, bytes);
                flash.addFlashAttribute("info", "Se ha subido correctamente la foto del cliente" + foto.getOriginalFilename() + "");

                cliente.setFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String mensajeFls = (cliente.getId() != null) ? "El registro de cliente se a editado con éxito" : "El registro de cliente se a creado con exito";
        clienteService.save(cliente);
        flash.addFlashAttribute("success", mensajeFls);
        return "redirect:listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("info", "El cliente no existe en la base de datos");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero");
            return "redurect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar del cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }
        return "redirect:/listar";

    }
}

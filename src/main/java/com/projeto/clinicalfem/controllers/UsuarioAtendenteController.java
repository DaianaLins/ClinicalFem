package com.projeto.clinicalfem.controllers;

import com.projeto.clinicalfem.models.UsuarioAtendente;
import com.projeto.clinicalfem.service.UsuarioAtendenteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class UsuarioAtendenteController {
    
    UsuarioAtendenteService service;
    public UsuarioAtendenteController(UsuarioAtendenteService serv) {
        service = serv;
    }

    @GetMapping("/cadastroAtendente")
    public ModelAndView cadUsuA() {
        ModelAndView modelo = new ModelAndView("usuarioatendenteform");
        modelo.addObject("usuarioatendente", new UsuarioAtendente());
        modelo.addObject("emailrepetido", "");
        return modelo;
    }
}

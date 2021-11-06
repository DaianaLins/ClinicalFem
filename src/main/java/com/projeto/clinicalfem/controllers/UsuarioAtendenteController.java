package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.UsuarioAtendente;
import com.projeto.clinicalfem.service.UsuarioAtendenteService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuarioatendenteform");
        modelo.addObject("usuarioatendente", new UsuarioAtendente());
        
        return modelo;
    }

    @PostMapping("/cadastroAtendente")
    public ModelAndView cadastrar(UsuarioAtendente atendente)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/loginAtendente");
        
        atendente.setAdm(false);
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncript = encoder.encode(atendente.getSenha());
        atendente.setSenha(senhaEncript);
        service.cadastrar(atendente);

       
        /*if (!service.cadastrar(atendente)) {
            modelo.setViewName("usuarioatendenteform");
            modelo.addObject("emailrepetido", "email já cadastrado");
            atendente.setId(null);
            modelo.addObject("usuarioatendente", atendente);
        }*/

        return modelo;
    }
}

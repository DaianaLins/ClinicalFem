package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.service.UsuarioMedicoService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class UsuarioMedicoController {
    UsuarioMedicoService service;
    public UsuarioMedicoController(UsuarioMedicoService serv) {
        service = serv;
    }

    @GetMapping("/cadastroMed")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuariomedicoform");
        modelo.addObject("usuariomedico", new UsuarioMedico());
        
        return modelo;
    }

    @PostMapping("/cadastroMed")
    public ModelAndView cadastrar(UsuarioMedico medico)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/medico/login");
        
        medico.setTipo(Perfil.MEDICO.toString());
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncript = encoder.encode(medico.getSenha());
        medico.setSenha(senhaEncript);
        service.cadastrar(medico);

       
        /*if (!service.cadastrar(atendente)) {
            modelo.setViewName("usuarioatendenteform");
            modelo.addObject("emailrepetido", "email já cadastrado");
            atendente.setId(null);
            modelo.addObject("usuarioatendente", atendente);
        }*/

        return modelo;
    }
}

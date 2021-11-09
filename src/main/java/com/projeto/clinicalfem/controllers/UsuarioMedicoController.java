package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.Usuarios;
import com.projeto.clinicalfem.service.UsuariosService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class UsuarioMedicoController {
    UsuariosService service;
    public UsuarioMedicoController(UsuariosService serv) {
        service = serv;
    }

    @GetMapping("/cadastroMedico")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuariomedicoform");
        modelo.addObject("usuariomedico", new Usuarios());
        
        return modelo;
    }

    @PostMapping("/cadastroMedico")
    public ModelAndView cadastrar(Usuarios medico)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/loginMedico");
        
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

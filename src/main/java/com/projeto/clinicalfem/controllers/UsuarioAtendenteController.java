package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.Usuarios;
import com.projeto.clinicalfem.service.UsuariosService;
import com.projeto.clinicalfem.enums.Perfil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class UsuarioAtendenteController {
    
    UsuariosService service;
    public UsuarioAtendenteController(UsuariosService serv) {
        service = serv;
    }

    @GetMapping("/cadastroAtendente")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuarioatendenteform");
        modelo.addObject("usuarioatendente", new Usuarios());
        
        return modelo;
    }

    @PostMapping("/cadastroAtendente")
    public ModelAndView cadastrar(Usuarios atendente)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/loginAtendente");
        System.out.print("funcionou?");
        atendente.setTipo(Perfil.ATENDENTE.toString());
        
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

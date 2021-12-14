package com.projeto.clinicalfem.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class TelaGeralController {


    @GetMapping("/")
    public ModelAndView telaInicial(){
        return inicio();
    }
   

    @GetMapping("/atendente/tela")
    public ModelAndView index(Principal principal) {
        ModelAndView modelo = new ModelAndView("telaGeral");
        System.out.println(principal.toString());
        return modelo;
    }
    
    @GetMapping("/pageHome")
    public ModelAndView inicio() {
        ModelAndView modelo = new ModelAndView("TelaInicial");
        return modelo;
    }
    @GetMapping("/paciente/telaPaciente")
    public ModelAndView indexP(Principal principal) {
        ModelAndView modelo = new ModelAndView("telaPaciente");
        System.out.println(principal.getName());
        return modelo;
    }

    @GetMapping("/medico/telaMedico")
    public ModelAndView indexM(Principal principal) {
        ModelAndView modelo = new ModelAndView("telaMedico");
       System.out.println(principal.getName());
        return modelo;
    }

    @GetMapping("/selecionar")
    public ModelAndView Selecionar() {
        ModelAndView modelo = new ModelAndView("telaselecionarlogin");
        return modelo;
    }

    @GetMapping("/atendente/login")
    public ModelAndView loginAt() {
        ModelAndView modelo = new ModelAndView("telaloginat");
        return modelo;
    }
    @GetMapping("/servicos")
    public ModelAndView servicos() {
        ModelAndView modelo = new ModelAndView("telaservicos");
        return modelo;
    }
    @GetMapping("paciente/login")
    public ModelAndView loginP() {
        ModelAndView modelo = new ModelAndView("telaloginP");
        return modelo;
    }
    @GetMapping("/medico/login")
    public ModelAndView loginMed() {
        ModelAndView modelo = new ModelAndView("telaloginMed");
        return modelo;
    }
    
    @GetMapping("/logout")
    public ModelAndView logout() {
    ModelAndView modelo = new ModelAndView("redirect:/pageHome");
    return modelo;
}
}
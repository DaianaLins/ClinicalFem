package com.projeto.clinicalfem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class TelaGeralController {

    @GetMapping("/tela")
    public ModelAndView index() {
        ModelAndView modelo = new ModelAndView("telaGeral");
        return modelo;
    }

    @GetMapping("/selecionar")
    public ModelAndView Selecionar() {
        ModelAndView modelo = new ModelAndView("telaselecionarlogin");
        return modelo;
    }

    @GetMapping("/loginAtendente")
    public ModelAndView loginAt() {
        ModelAndView modelo = new ModelAndView("telaloginat");
        return modelo;
    }
    @GetMapping("/loginPaciente")
    public ModelAndView loginP() {
        ModelAndView modelo = new ModelAndView("telaloginP");
        return modelo;
    }
    @GetMapping("/loginMedico")
    public ModelAndView loginMed() {
        ModelAndView modelo = new ModelAndView("telaloginMed");
        return modelo;
    }
}

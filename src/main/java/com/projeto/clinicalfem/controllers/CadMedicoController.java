package com.projeto.clinicalfem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class CadMedicoController {

    @GetMapping("/cadastrarMedico")
    public ModelAndView form() {
        ModelAndView modelo = new ModelAndView("formCadMedico");
        return modelo;
    }

    @GetMapping("/medicos")
    public ModelAndView medicos() {
        ModelAndView modelo = new ModelAndView("medicosCadastrados");
        return modelo;
    }
}

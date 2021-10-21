package com.projeto.clinicalfem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class CadPacienteController {

    @GetMapping("/cadastrarPaciente")
    public ModelAndView form() {
        ModelAndView modelo = new ModelAndView("formCadPaciente");
        return modelo;
    }

    @GetMapping("/pacientes")
    public ModelAndView pacientes() {
        ModelAndView modelo = new ModelAndView("pacientesCadastrados");
        return modelo;
    }
}

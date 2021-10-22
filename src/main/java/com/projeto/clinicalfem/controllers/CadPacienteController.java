package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.CadPaciente;
import com.projeto.clinicalfem.service.CadPacienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class CadPacienteController {

    CadPacienteService service;
    public CadPacienteController(CadPacienteService serv){
        service= serv;
    }

    @GetMapping("/cadastrarPaciente")
    public ModelAndView cadastrarPaciente() {
        ModelAndView modelo = new ModelAndView("formCadPaciente");
        modelo.addObject("cadPaciente", new CadPaciente());
        return modelo;
    }
    @PostMapping("/cadastrarPaciente")
    public ModelAndView cadastrarPaciente(CadPaciente cadPaciente) {
        ModelAndView modelo = new ModelAndView("redirect:/pacientes");
        service.cadastrar(cadPaciente);

        return modelo;
    }

    @GetMapping("/pacientes")
    public ModelAndView pacientes() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("pacientesCadastrados");
        modelo.addObject("cadpacientes", service.getAllCadPacientes());
        return modelo;
    }
    //fala gay já mandei o link gay ta bom
   

}

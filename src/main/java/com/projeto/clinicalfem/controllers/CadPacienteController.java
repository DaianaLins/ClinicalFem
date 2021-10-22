package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.CadPaciente;
import com.projeto.clinicalfem.service.CadPacienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        modelo.addObject("cadpaciente", new CadPaciente());
        return modelo;
    }
    @PostMapping("/cadastrarPaciente")
    public ModelAndView cadastrarPaciente(CadPaciente cadpaciente) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/pacientes");
        service.cadastrar(cadpaciente);

        return modelo;
    }

    @GetMapping("/pacientes")
    public ModelAndView pacientes() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("pacientesCadastrados");
        modelo.addObject("cadpacientes", service.getAllCadPacientes());
        return modelo;
    }
    
    @GetMapping("/{id}/deletarPaciente")
	public ModelAndView deletarPaciente(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/pacientes");
        service.deletar(id);
		return modelo;
	}
    @GetMapping("/{id}/alterarPaciente")
	public ModelAndView alterarPaciente(@PathVariable String id) throws InterruptedException, ExecutionException{
		ModelAndView mv = new ModelAndView("alterarPaciente");

		mv.addObject("cadpaciente", service.getCadPacienteById(id));
	
		return mv;
	}
    @PostMapping("/alterarPaciente")
	public ModelAndView alterarPaciente(CadPaciente cadpaciente) throws InterruptedException, ExecutionException{
		ModelAndView modelo = new ModelAndView("redirect:/pacientes");
		service.editar(cadpaciente);
		return modelo;
	}
}

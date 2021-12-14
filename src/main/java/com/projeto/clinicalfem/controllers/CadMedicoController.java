    package com.projeto.clinicalfem.controllers;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.CadMedico;
import com.projeto.clinicalfem.service.CadMedicoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class CadMedicoController {


    /**Revisão ok */
    CadMedicoService service;
    public CadMedicoController(CadMedicoService serv) {
        service = serv;
    }

    @GetMapping("/cadastrarMedico")
    public ModelAndView cadastrarMedico() {
        ModelAndView modelo = new ModelAndView("formCadMedico");
        modelo.addObject("cadmedico", new CadMedico());
        return modelo;
    }
    @PostMapping("/cadastrarMedico")
    public ModelAndView cadastrarMedico(CadMedico cadmedico) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/medicos");
        service.cadastrar(cadmedico);
        return modelo;
    }
    @GetMapping("/medicos")
    public ModelAndView medicos() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("medicosCadastrados");
        modelo.addObject("cadmedicos", service.getAllCadMedicos());
        return modelo;
    }
    @GetMapping("/{cod}/deletarMedico")
    public ModelAndView deletarMedico(@PathVariable String cod){
        ModelAndView modelo = new ModelAndView("redirect:/medicos");
        service.deletar(cod);
        return modelo;
    }
    @GetMapping("/{cod}/alterarMedico")
    public ModelAndView alterarMedico(@PathVariable String cod) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("alterarMedico");
        modelo.addObject("cadmedico", service.getCadMedicoByCod(cod));
        
        return modelo;
    }
    @PostMapping("/alterarMedico")
    public ModelAndView alterarMedico(CadMedico cadmedico) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/medicos");
        
        service.editar(cadmedico);
        return modelo;
    }
}

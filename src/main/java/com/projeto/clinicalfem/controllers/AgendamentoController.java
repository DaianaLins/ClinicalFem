package com.projeto.clinicalfem.controllers;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.Agendamento;
import com.projeto.clinicalfem.service.AgendamentoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * AgendamentoController
 */
@RestController
@RequestMapping("/")
public class AgendamentoController {

    AgendamentoService service;
	public AgendamentoController(AgendamentoService serv){
        service = serv;
    }

    @GetMapping("/agendarConsulta")
    public ModelAndView agendar() {
        ModelAndView modelo = new ModelAndView("formAgendamento");
        modelo.addObject("agendamento", new Agendamento());
        return modelo;
    }

    @PostMapping("/agendarConsulta")
    public ModelAndView agendarpost(Agendamento agendamento) throws InterruptedException, ExecutionException{
            ModelAndView modelo = new ModelAndView("redirect:/consultas");
            service.cadastrar(agendamento);
            return modelo;
    }

    @GetMapping("/consultas")
    public ModelAndView consultas() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("consultasAgendadas");

		modelo.addObject("agendamentos", service.getAllAgendamentos());
		return modelo;
    }

    @GetMapping("/{codigo}/deletarAgendamento")
	public ModelAndView deletarAgendamento(@PathVariable String codigo) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/consultas");
        service.deletar(codigo);
		return modelo;
	}

    @GetMapping("/{codigo}/alterarAgendamento")
	public ModelAndView alterarAgendamento(@PathVariable String codigo) throws InterruptedException, ExecutionException{
		ModelAndView mv = new ModelAndView("alterarAgendamento");

		mv.addObject("agendamento", service.getAgendamentoByCodigo(codigo));
	
		return mv;
	}
    @PostMapping("/alterarAgendamento")
	public ModelAndView alterarAgendamento(Agendamento agendamento) throws InterruptedException, ExecutionException{
		ModelAndView modelo = new ModelAndView("redirect:/consultas");
		service.editar(agendamento);
		return modelo;
	}
}
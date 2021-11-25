package com.projeto.clinicalfem.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.Agendamento;
import com.projeto.clinicalfem.models.CadMedico;
import com.projeto.clinicalfem.models.CadPaciente;
import com.projeto.clinicalfem.service.AgendamentoService;
import com.projeto.clinicalfem.service.CadMedicoService;
import com.projeto.clinicalfem.service.CadPacienteService;

import org.springframework.ui.Model;
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
        CadPacienteService servPaciente;
        CadMedicoService servicemed;
        public AgendamentoController(AgendamentoService serv, CadPacienteService servp, CadMedicoService servicem){
            service = serv;
            servPaciente = servp;
            servicemed = servicem;
        }

   
    @GetMapping("/agendarConsulta")
    public ModelAndView agendar() throws InterruptedException, ExecutionException {
        List<CadMedico> cadmedico = servicemed.getAllCadMedicos() ;
        ModelAndView modelo = new ModelAndView("formAgendamento");
        modelo.addObject("cadmedicos", cadmedico);
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

    @GetMapping("/{codigo}/detalhesConsulta")
	public ModelAndView detalhesConsulta(@PathVariable String codigo) throws InterruptedException, ExecutionException {
		Agendamento agendamento = service.getAgendamentoByCodigo(codigo);

        CadPaciente cadpaciente = servPaciente.getCadPacienteByCPF(agendamento.getCpf());

		ModelAndView mv = new ModelAndView("detalhes");
		mv.addObject("agendamentos", agendamento);
        mv.addObject("paciente", cadpaciente);
		return mv;
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
        List<CadMedico> cadmedico = servicemed.getAllCadMedicos() ;
		mv.addObject("agendamento", service.getAgendamentoByCodigo(codigo));
        mv.addObject("cadmedicos", cadmedico);
		return mv;
	}
   
    @PostMapping("/alterarAgendamento")
	public ModelAndView alterarAgendamento(Agendamento agendamento) throws InterruptedException, ExecutionException{
		ModelAndView modelo = new ModelAndView("redirect:/consultas");
		service.editar(agendamento);
		return modelo;
	}
}
package com.projeto.clinicalfem.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.clinicalfem.models.Noticia;
import com.projeto.clinicalfem.models.UsuarioAtendente;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.models.UsuarioParse;
import com.projeto.clinicalfem.models.UsuariosSpring;
import com.projeto.clinicalfem.service.CadMedicoService;
import com.projeto.clinicalfem.service.CadPacienteService;
import com.projeto.clinicalfem.service.NoticiaService;
import com.projeto.clinicalfem.service.UsuarioAtendenteService;
import com.projeto.clinicalfem.service.UsuarioMedicoService;
import com.projeto.clinicalfem.service.UsuarioPacienteService;

@RestController
@RequestMapping("/")
public class NoticiaController {
	
    NoticiaService service;
    UsuarioPacienteService services;
    CadPacienteService servPaciente;
    UsuarioMedicoService serviceM;
    CadMedicoService servMedico;
    UsuarioAtendenteService serviceA;
    public NoticiaController(NoticiaService serv, UsuarioPacienteService serv3, CadPacienteService servp, UsuarioMedicoService servmed,  CadMedicoService serv2, UsuarioAtendenteService serv4){
        service= serv;
        services = serv3;
        servPaciente = servp; 
        serviceM = servmed;
        servMedico = serv2;
        serviceA = serv4;
    }

   
    //Noticia atendente
    @GetMapping("atendente/noticias")
    public ModelAndView getNoticias(Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticias");
        UsuarioAtendente usuariopaciente = serviceA.getMembroByEmail(principal.getName());
        List<Noticia> noticias = service.getAllNoticias();
        modelo.addObject("noticias", noticias);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    
    //Noticia Paciente
    @GetMapping("paciente/noticias")
    public ModelAndView getNoticiasPaciente(Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticias");
        UsuariosSpring usuariopaciente = UsuarioParse.toSpring(services.getMembroByEmail(principal.getName()));
        List<Noticia> noticias = service.getAllNoticias();
        modelo.addObject("noticias", noticias);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    //Noticias Médico
    @GetMapping("medico/noticias")
    public ModelAndView getNoticiasMedico(Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticias");
        UsuarioMedico usuariopaciente = serviceM.getMembroByEmail(principal.getName());
        List<Noticia> noticias = service.getAllNoticias();
        modelo.addObject("noticias", noticias);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    //Atendente
    @GetMapping("atendente/{id}/noticias")
    public ModelAndView getNoticiasDetailsA(@PathVariable String id, Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticiaDetails");
        UsuarioAtendente usuariopaciente = serviceA.getMembroByEmail(principal.getName());
        Noticia noticia = service.getNoticiaById(id);
        modelo.addObject("noticia", noticia);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    //Paciente
    @GetMapping("paciente/{id}/noticias")
    public ModelAndView getNoticiasDetailsP(@PathVariable String id, Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticiaDetails");
        UsuariosSpring usuariopaciente = UsuarioParse.toSpring(services.getMembroByEmail(principal.getName()));
        Noticia noticia = service.getNoticiaById(id);
        modelo.addObject("noticia", noticia);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    @GetMapping("medico/{id}/noticias")
    public ModelAndView getNoticiasDetailsM(@PathVariable String id, Principal principal) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticiaDetails");
        UsuarioMedico usuariopaciente = serviceM.getMembroByEmail(principal.getName());
        Noticia noticia = service.getNoticiaById(id);
        modelo.addObject("noticia", noticia);
        modelo.addObject("usuariopaciente", usuariopaciente);
        return modelo;
    }
    @GetMapping("/novaNoticia")
    public ModelAndView getNoticiaFom() {
        ModelAndView modelo = new ModelAndView("noticiaform");
        return modelo;
    }
    @PostMapping("/novaNoticia")
    public ModelAndView getNoticiaFom(Noticia noticia) throws InterruptedException, ExecutionException{
        
        ModelAndView modelo = new ModelAndView("redirect:/atendente/noticias");  
            noticia.setData(LocalDate.now().toString());
            service.salvar(noticia);
            return modelo;
    }
}
package com.projeto.clinicalfem.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.Service.Listener;
import com.google.firebase.database.ServerValue;
import com.projeto.clinicalfem.models.Noticia;
import com.projeto.clinicalfem.service.NoticiaService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/")
public class NoticiaController {

    NoticiaService service;
    public NoticiaController(NoticiaService serv){
        service= serv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelo = new ModelAndView("index");
        return modelo;
    }
    @GetMapping("/noticias")
    public ModelAndView getNoticias() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticias");
        List<Noticia> noticias = service.getAllNoticias();
        modelo.addObject("noticias", noticias);
        return modelo;
    }
    @GetMapping("/{id}/noticias")
    public ModelAndView getNoticiasDetails(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("noticiaDetails");
        Noticia noticia = service.getNoticiaById(id);
        modelo.addObject("noticia", noticia);
        return modelo;
    }
    @GetMapping("/novaNoticia")
    public ModelAndView getNoticiaFom() {
        ModelAndView modelo = new ModelAndView("noticiaform");
        return modelo;
    }
    @PostMapping("/novaNoticia")
    public ModelAndView getNoticiaFom(Noticia noticia, long timeStamp) throws InterruptedException, ExecutionException{
        
        ModelAndView modelo = new ModelAndView("redirect:/noticias");  
        Map<String, Object> value = new HashMap<>();
            value.put("timestamp", ServerValue.TIMESTAMP);
            service.salvar(noticia);
            return modelo;
    }
}

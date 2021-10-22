package com.projeto.clinicalfem.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.Service.Listener;
import com.projeto.clinicalfem.models.Noticia;
import com.projeto.clinicalfem.service.NoticiaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}

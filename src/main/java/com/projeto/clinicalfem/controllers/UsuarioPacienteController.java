package com.projeto.clinicalfem.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.CropImageToSquare;
import com.projeto.clinicalfem.models.UsuarioParse;
import com.projeto.clinicalfem.models.Usuarios;
import com.projeto.clinicalfem.models.UsuariosSpring;
import com.projeto.clinicalfem.service.UsuarioPacienteService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/")
public class UsuarioPacienteController{
   
    UsuarioPacienteService service;
    public UsuarioPacienteController(UsuarioPacienteService serv){
        service = serv;
    }

    @GetMapping("/{id}")
    public ModelAndView getPacienteDetails(@PathVariable String id) throws InterruptedException, ExecutionException, IOException{
        ModelAndView modelo = new ModelAndView("detalhespaciente");
        
        UsuariosSpring paciente = UsuarioParse.toSpring(service.getMembroById(id));
      
        Path path = Paths.get("src/main/resources/static/imagens/");        
        if(path.toFile().exists()){
            Files.delete(path);
        }
        if (paciente.getImagem() != null) {
            Files.write(path, paciente.getImagem());
        }

        TimeUnit.SECONDS.sleep(2);

        modelo.addObject("paciente", paciente);

        return modelo;
    }

    @GetMapping("/calendario")
    public ModelAndView calendario() {
        ModelAndView modelo = new ModelAndView("calendario.html");
        
        return modelo;
    }

    @GetMapping("/cadastroPaciente")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuariopacienteform.html");
        modelo.addObject("usuariopaciente", new Usuarios());
        
        return modelo;
    }
   
    @PostMapping("/cadastroPaciente")
    public ModelAndView cadastrar(@RequestParam("file") MultipartFile file, Usuarios usu)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/paciente/login");
        
        usu.setTipo(Perfil.PACIENTE.toString());
        

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncriptada = encoder.encode(usu.getSenha());
        usu.setSenha(senhaEncriptada);

        if (!file.isEmpty()) {
            try {
                // tranforma a imagem em Bytes
                byte[] bytes = file.getBytes();
                //diz o caminho pra onde a imagem vai ser armazenada
                Path path = Paths.get("src/main/resources/static/imagens/" + file.getOriginalFilename());
                //cria o arquivo na pasta solicitada
                Files.write(path, bytes);

                //diminui o tamanho máximo da imagem pra 300x300
                Thumbnails.of(path.toFile()).size(300, 300).allowOverwrite(true).toFile(path.toFile());

                //corta a imagem em um quadrado
                CropImageToSquare.crop(path);

                usu.setImagemLocal(Files.readAllBytes(path));
                Files.delete(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TimeUnit.SECONDS.sleep(2);
        
        if (!service.cadastrar(usu)) {
            modelo.setViewName("usuariopacienteform");
            usu.setId(null);
            modelo.addObject("usuariopaciente", usu);
        }

        return modelo;
    }
}
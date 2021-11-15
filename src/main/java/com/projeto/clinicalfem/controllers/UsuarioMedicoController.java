package com.projeto.clinicalfem.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.CropImageToSquare;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.service.UsuarioMedicoService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/")
public class UsuarioMedicoController {
    UsuarioMedicoService service;
    public UsuarioMedicoController(UsuarioMedicoService serv) {
        service = serv;
    }

    @GetMapping("/cadastroMed")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuariomedicoform");
        modelo.addObject("usuariomedico", new UsuarioMedico());
        
        return modelo;
    }

    
    @PostMapping("/cadastroMed")
    public ModelAndView cadastrar(@RequestParam("file") MultipartFile file, UsuarioMedico medico)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/medico/login");
        
        medico.setTipo(Perfil.MEDICO.toString());
        

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncriptada = encoder.encode(medico.getSenha());
        medico.setSenha(senhaEncriptada);

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

                medico.setImagemLocal(Files.readAllBytes(path));
                Files.delete(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TimeUnit.SECONDS.sleep(2);
        
        if (!service.cadastrar(medico)) {
            modelo.setViewName("usuariomedicoform");
            medico.setId(null);
            modelo.addObject("usuariomedico", medico);
        }

        return modelo;
    }
}

package com.projeto.clinicalfem.controllers;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.CadMedico;
import com.projeto.clinicalfem.models.CadPaciente;
import com.projeto.clinicalfem.models.CropImageToSquare;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.models.UsuarioParse;
import com.projeto.clinicalfem.models.Usuarios;
import com.projeto.clinicalfem.models.UsuariosSpring;
import com.projeto.clinicalfem.service.CadMedicoService;
import com.projeto.clinicalfem.service.CadPacienteService;
import com.projeto.clinicalfem.service.UsuarioMedicoService;
import com.projeto.clinicalfem.service.UsuarioPacienteService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/")
public class UsuarioPacienteController{

    private static String caminhoImagens = "src/main/resources/static/imagens/";
   
    UsuarioPacienteService service;
    CadPacienteService servPaciente;
    UsuarioMedicoService serviceM;
    CadMedicoService servMedico;
    public UsuarioPacienteController(UsuarioPacienteService serv, CadPacienteService servp, UsuarioMedicoService servmed,  CadMedicoService serv2){
        service = serv;
        servPaciente = servp; 
        serviceM = servmed;
        servMedico = serv2;
    }

    @GetMapping("/paciente/dados")
    public ModelAndView dados(Principal principal) throws InterruptedException, ExecutionException {
    	
        UsuariosSpring usuariopaciente = UsuarioParse.toSpring(service.getMembroByEmail(principal.getName()));
        CadPaciente cadpaciente = servPaciente.getCadPacienteByCPF(usuariopaciente.getCpf());
        ModelAndView mv = new ModelAndView("detalhesPaciente");
    	
        mv.addObject("cadpaciente", cadpaciente);
    	mv.addObject("usuariopaciente", usuariopaciente);
    	return mv;
    }
        
    @GetMapping("/paciente/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("No IF");
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
    @GetMapping("/paciente/mostrarImagemMed/{imagem}")
	@ResponseBody
	public byte[] retornarImagemMed(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			System.out.println("No IF");
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
            
    @GetMapping("/paciente/medicoclin")
    public ModelAndView getMedicos() throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("medicosclinica.html");
        List<UsuarioMedico> usuariomedico = serviceM.getAllUsuarios();
        /** List<CadMedico> cadmedico =  servMedico.getCadMedicoByCRM(usuariomedico.getCrm());
        modelo.addObject("cadmedico", cadmedico);
        **/
        modelo.addObject("usuariomedico", usuariomedico);
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
                Path path = Paths.get(caminhoImagens+String.valueOf(usu.getId())+file.getOriginalFilename());
                //cria o arquivo na pasta solicitada
                Files.write(path, bytes);

                //diminui o tamanho máximo da imagem pra 300x300
                Thumbnails.of(path.toFile()).size(300, 300).allowOverwrite(true).toFile(path.toFile());

                //corta a imagem em um quadrado
                CropImageToSquare.crop(path);
               
                usu.setImagemLocal(Files.readAllBytes(path));
                usu.setNomeImagem(String.valueOf(usu.getId())+file.getOriginalFilename());
                

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TimeUnit.SECONDS.sleep(3);
        
        if (!service.cadastrar(usu)) {
            modelo.setViewName("usuariopacienteform");
            usu.setId(null);
            modelo.addObject("usuariopaciente", usu);
        }

        return modelo;
    }
}
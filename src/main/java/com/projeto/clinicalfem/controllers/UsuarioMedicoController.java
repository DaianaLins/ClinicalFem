package com.projeto.clinicalfem.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.websocket.server.PathParam;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.models.Agendamento;
import com.projeto.clinicalfem.models.CadMedico;
import com.projeto.clinicalfem.models.CadPaciente;
import com.projeto.clinicalfem.models.CropImageToSquare;
import com.projeto.clinicalfem.models.Historico;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.models.UsuarioMedicoParse;
import com.projeto.clinicalfem.models.UsuarioMedicoSpring;
import com.projeto.clinicalfem.models.UsuarioParse;
import com.projeto.clinicalfem.models.Usuarios;
import com.projeto.clinicalfem.models.UsuariosSpring;
import com.projeto.clinicalfem.service.AgendamentoService;
import com.projeto.clinicalfem.service.CadMedicoService;
import com.projeto.clinicalfem.service.CadPacienteService;
import com.projeto.clinicalfem.service.HistoricoSevice;
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
public class UsuarioMedicoController {

    private static String caminhoImagens = "src/main/resources/static/imagens/";

    UsuarioMedicoService service;
    CadMedicoService servMedico;
    UsuarioPacienteService servicep;
    AgendamentoService servAgenda;
    CadPacienteService servPaci;
    HistoricoSevice servH;
    public UsuarioMedicoController(UsuarioMedicoService serv, UsuarioPacienteService servp ,CadMedicoService servm,  AgendamentoService servA, CadPacienteService servP, HistoricoSevice servhis) {
        service = serv;
        servMedico = servm;
        servicep = servp;
        servAgenda = servA;
        servPaci = servP;
        servH = servhis;
    }

    @GetMapping("/medico/pacientesclin")
    public ModelAndView verPacientes() throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("pacientesclinica.html");
        List<UsuariosSpring> usuarios = new ArrayList<>();
        List<Usuarios> usuariopaciente = servicep.getAllUsuarios();
        for(Usuarios user : usuariopaciente ){
            usuarios.add(UsuarioParse.toSpring(user));
      }
        modelo.addObject("usuariopaciente", usuarios);
        return modelo;
    }
    @GetMapping("/medico/verificarConsultas")
    public ModelAndView consultas(Principal principal) throws InterruptedException, ExecutionException{
        UsuarioMedicoSpring usuariomedico = UsuarioMedicoParse.toSpring(service.getMembroByEmail(principal.getName()));
        Agendamento agendamento = servAgenda.getAgendamentoByMedico(usuariomedico.getNome());
        List<Agendamento> agend = servAgenda.getAllAgendamentos();
        List<CadMedico> cadmedico = servMedico.getAllCadMedicos();
        ModelAndView modelo = new ModelAndView("verificarcons.html");
        
        modelo.addObject("agendamento", agendamento);
        modelo.addObject("agend", agend);
        modelo.addObject("cadmedico", cadmedico);
    	modelo.addObject("usuariomedico", usuariomedico);
        return modelo;
    }
    @GetMapping("/medico/{codigo}/deletarConsulta")
	public ModelAndView deletarConsulta(@PathVariable String codigo) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/medico/verificarConsultas");
        servAgenda.deletar(codigo);
		return modelo;
	}

    @GetMapping("/medico/cadastrarhistorico/{id}")
    public ModelAndView historico(@PathVariable String id, Principal principal)throws InterruptedException, ExecutionException {
        List<CadPaciente> cadpaciente = servPaci.getAllCadPacientes();
        Usuarios paciente = servicep.getMembroById(id);
        UsuarioMedicoSpring usuariomedico = UsuarioMedicoParse.toSpring(service.getMembroByEmail(principal.getName()));
        ModelAndView modelo = new ModelAndView("historico");
        modelo.addObject("paciente", paciente); 
        modelo.addObject("cadpaciente", cadpaciente);
        modelo.addObject("medico", usuariomedico);
        modelo.addObject("historico", new Historico());
        CadPaciente cpaciente = servPaci.getCadPacienteById(id);
        Historico chistorico = servH.getHistoricoById(cpaciente.getNome());
        modelo.addObject("cpaciente", cpaciente); 
        modelo.addObject("chistorico", chistorico);
        return modelo;
    }
    @GetMapping("/medico/visualizarHistorico/{id}")
    public ModelAndView visualizarHistorico(@PathVariable String id) throws InterruptedException, ExecutionException {
        CadPaciente paciente = servPaci.getCadPacienteById(id);
        Historico historico = servH.getHistoricoById(paciente.getNome());
        ModelAndView mv = new ModelAndView("visualizarHistorico.html");
        mv.addObject("paciente", paciente); 
        mv.addObject("historico", historico);
    	return mv;
    }
        
    @PostMapping("/medico/cadastrarhistorico/{id}")
	public ModelAndView historico(Historico historico) throws InterruptedException, ExecutionException{
		ModelAndView modelo = new ModelAndView("redirect:/medico/pacientesclin");
		servH.salvar(historico);

		return modelo;
	}
    @GetMapping("/{id}/alterarHistorico")
    public ModelAndView alterarMedico(@PathVariable String id) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("alterarHistorico");
        modelo.addObject("historico", servH.getHistoricoById(id));
        
        return modelo;
    }
    @PostMapping("/medico/alterarHistorico")
    public ModelAndView alterarMedico(Historico historico) throws InterruptedException, ExecutionException{
        ModelAndView modelo = new ModelAndView("redirect:/medico/pacientesclin");
        
        servH.editar(historico);
        return modelo;
    }

    @GetMapping("/medico/dados")
    public ModelAndView dados(Principal principal) throws InterruptedException, ExecutionException {
    	 UsuarioMedicoSpring usuariomedico = UsuarioMedicoParse.toSpring(service.getMembroByEmail(principal.getName()));
         CadMedico cadmedico =  servMedico.getCadMedicoByCRM(usuariomedico.getCrm());
        
        ModelAndView mv = new ModelAndView("detalhesMedico");
          mv.addObject("cadmedico", cadmedico);
    	  mv.addObject("usuariomedico", usuariomedico);
    	return mv;
    }
        
    @GetMapping("/medico/mostrarImagem/{imagem}")
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
        


    @GetMapping("/cadastroMedico")
    public ModelAndView cadastrar() {
        ModelAndView modelo = new ModelAndView("usuariomedicoform.html");
        modelo.addObject("usuariomedico", new UsuarioMedico());
        
        return modelo;
    }
   
   
    @PostMapping("/cadastroMedico")
    public ModelAndView cadastrar(@RequestParam("file") MultipartFile file, UsuarioMedico usu)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/medico/login");
        
        usu.setTipo(Perfil.MEDICO.toString());
        

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
            modelo.setViewName("usuariomedicoform");
            usu.setId(null);
            modelo.addObject("usuariomedico", usu);
        }

        return modelo;
    }
    @GetMapping("/medico/{cod}/editar")
    public ModelAndView editar(@PathVariable String cod, Principal principal) throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("usuariomedicoeditar");
        CadMedico cadmedico = servMedico.getCadMedicoByCod(cod);
        UsuarioMedico usuariomedico = service.getMembroByEmail(principal.getName());
        
        System.out.println(cadmedico.getCpf());
        modelo.addObject("usuariomedico", usuariomedico);
        
        modelo.addObject("cadmedico", cadmedico);

        return modelo;
    }

    @PostMapping("/medico/{cod}/editar")
    public ModelAndView editar(@RequestParam("file") MultipartFile file, UsuarioMedico usu)
            throws InterruptedException, ExecutionException {
        ModelAndView modelo = new ModelAndView("redirect:/medico/dados");

        if(!usu.getSenha().isEmpty()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String senhaEncriptada = encoder.encode(usu.getSenha());
            usu.setSenha(senhaEncriptada);
        }else{
            usu.setSenha(service.getMembroById(usu.getId()).getSenha());
        }

        usu.setTipo(Perfil.MEDICO.toString());

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

        if (!service.editar(usu)) {
            modelo.setViewName("usuariomedicoeditar");
            modelo.addObject("usuariomedico", usu);
        }

        return modelo;
    }
    @GetMapping("/medico/{cod}/excluir")
    public ModelAndView excluir(@PathVariable String cod) {
        ModelAndView modelo = new ModelAndView("redirect:/paciente/login");
        service.apagar(cod);
        servMedico.deletar(cod);
        return modelo;
    }
}

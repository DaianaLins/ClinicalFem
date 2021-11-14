package com.projeto.clinicalfem.service;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.UserDetailsImplPaciente;
import com.projeto.clinicalfem.models.Usuarios;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplServicePaciente implements UserDetailsService{
    UsuarioPacienteService service;

    public UserDetailsImplServicePaciente(UsuarioPacienteService service){
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = null;
        // TODO Auto-generated method stub
        try{
            usuario = service.getMembroByEmail(username);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        if (usuario.getId() == null || usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new UserDetailsImplPaciente(usuario);
    }
}

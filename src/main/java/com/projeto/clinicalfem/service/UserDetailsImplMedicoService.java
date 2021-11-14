package com.projeto.clinicalfem.service;

import java.util.concurrent.ExecutionException;


import com.projeto.clinicalfem.service.UsuarioMedicoService;
import com.projeto.clinicalfem.models.UsuarioMedico;
import com.projeto.clinicalfem.models.UserDetailsImplMedico;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplMedicoService implements UserDetailsService{
    UsuarioMedicoService service;

    public UserDetailsImplMedicoService(UsuarioMedicoService service){
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioMedico usuario = null;
        // TODO Auto-generated method stub
        try{
            usuario = service.getMembroByEmail(username);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        if (usuario.getId() == null || usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new UserDetailsImplMedico(usuario);
    }
}

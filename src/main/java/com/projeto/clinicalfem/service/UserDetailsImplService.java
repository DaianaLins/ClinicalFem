package com.projeto.clinicalfem.service;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.UserDetailsImpl;
import com.projeto.clinicalfem.models.UsuarioAtendente;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsImplService implements UserDetailsService{
    UsuarioAtendenteService service;

    public UserDetailsImplService(UsuarioAtendenteService service){
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioAtendente atendente = null;
        // TODO Auto-generated method stub
        try{
            atendente = service.getUsuarioAtendenteByEmail(username);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        if (atendente.getId() == null || atendente == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new UserDetailsImpl(atendente);
    }
}

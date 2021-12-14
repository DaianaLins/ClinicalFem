package com.projeto.clinicalfem.service;

import java.util.concurrent.ExecutionException;

import com.projeto.clinicalfem.models.UserDetailsImplAtendente;
import com.projeto.clinicalfem.models.UsuarioAtendente;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**Revisão ok */

@Service
public class UserDetailsImplAtendenteService implements UserDetailsService{
    UsuarioAtendenteService service;

    public UserDetailsImplAtendenteService(UsuarioAtendenteService service){
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioAtendente usuario = null;
        // TODO Auto-generated method stub
        try{
            usuario = service.getMembroByEmail(username);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        if (usuario.getId() == null || usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new UserDetailsImplAtendente(usuario);
    }
}

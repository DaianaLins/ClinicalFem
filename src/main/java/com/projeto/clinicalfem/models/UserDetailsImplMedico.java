package com.projeto.clinicalfem.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
/**Revisão ok */
public class UserDetailsImplMedico implements UserDetails{

    private UsuarioMedico medico;

    public UserDetailsImplMedico(UsuarioMedico medico){
        this.medico = medico;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        String perfil = medico.getTipo();
        return AuthorityUtils.createAuthorityList(perfil);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return medico.getSenha();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return medico.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}

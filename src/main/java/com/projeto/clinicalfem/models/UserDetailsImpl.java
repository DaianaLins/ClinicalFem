package com.projeto.clinicalfem.models;

import java.util.Collection;

import com.projeto.clinicalfem.enums.Perfil;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

    private UsuarioAtendente usuarioatendente;

    public UserDetailsImpl(UsuarioAtendente usuarioatendente){
        this.usuarioatendente = usuarioatendente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Perfil perfil = usuarioatendente.isAdm() ? Perfil.ADMIN : Perfil.USER;
        
        return AuthorityUtils.createAuthorityList(perfil.toString());
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return usuarioatendente.getSenha();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return usuarioatendente.getEmail();
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

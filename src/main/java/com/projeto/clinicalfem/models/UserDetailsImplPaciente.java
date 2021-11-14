package com.projeto.clinicalfem.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplPaciente implements UserDetails{

    private Usuarios paciente;

    public UserDetailsImplPaciente(Usuarios paciente){
        this.paciente = paciente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        String perfil = paciente.getTipo();
        return AuthorityUtils.createAuthorityList(perfil);
        //só isso ja basta k KKK OXI
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return paciente.getSenha();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return paciente.getEmail();
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

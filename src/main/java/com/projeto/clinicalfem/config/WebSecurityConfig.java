package com.projeto.clinicalfem.config;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.service.UserDetailsImplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
   
    @Autowired
    private UserDetailsImplService detalhes;
   
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.authorizeRequests()
       .antMatchers("/css/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/materialize/**").permitAll()
        .antMatchers("/pageHome").permitAll()
        .antMatchers("/servicos").permitAll()
        .antMatchers("/selecionar").permitAll()
        .antMatchers("/cadastroAtendente").permitAll()
        .antMatchers("/cadastrarPaciente").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/cadastrarMedico").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/alterarPaciente").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/alterarMedico").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/agendarConsulta").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/alterarAgendamento").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/pacientes").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/medicos").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/consultas/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/{codigo}/detalhesConsulta").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/{codigo}/deletarAgendamento").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("{cod}/deletarMedico").hasAuthority(Perfil.ADMIN.toString())
        .anyRequest().authenticated();

       http.formLogin().loginPage("/loginAtendente")
       .defaultSuccessUrl("/telaGeral")
        .permitAll();
       
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
}

package com.projeto.clinicalfem.config;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.service.UserDetailsImplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
   
    @Autowired
    private UserDetailsImplService detalhes;
   
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.authorizeRequests()
        .antMatchers("/pageHome").permitAll()
        .antMatchers("/servicos").permitAll()
        .antMatchers("/selecionar").permitAll()
        .antMatchers("/loginPaciente").permitAll()
        .antMatchers("/loginAtendente").permitAll()
        .antMatchers("/loginMedico").permitAll()
        .antMatchers("/cadastrarAtendente").permitAll()
        .antMatchers("/cadastrarPaciente").permitAll()
        .antMatchers("/cadastrarMedico").permitAll()
        .antMatchers("/alterarPaciente").permitAll()
        .antMatchers("/alterarMedico").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/agendarConsulta").permitAll()
        .antMatchers("/alterarAgendamento").permitAll()
        .antMatchers("/pacientes").permitAll()
        .antMatchers("/medicos").permitAll()
        .antMatchers("/tela").permitAll()
        .antMatchers("/consultas").permitAll()
        .antMatchers("/detalhesConsulta").permitAll()
        .antMatchers("/deletarAgendamento").permitAll()
        .antMatchers("/deletarMedico").permitAll()
        .anyRequest().authenticated()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

       http.formLogin().loginPage("/loginAtendente")
       .defaultSuccessUrl("/tela")
        .permitAll();
       
       
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/static/**" ,"/bootstrap/**" ,"/style/**");
	}
}

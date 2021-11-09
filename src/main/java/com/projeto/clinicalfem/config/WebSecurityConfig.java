package com.projeto.clinicalfem.config;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.service.UserDetailsImplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        
        @Autowired
        private UserDetailsImplService detalhes;

        public App1ConfigurationAdapter() {
            super();
        }

        // vamo tentar assim com coisos individuais k

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/pageHome/").permitAll().antMatchers("/").permitAll().antMatchers("/servicos/").permitAll()
                    .antMatchers("/selecionar/").permitAll().antMatchers("/loginPaciente/").permitAll()
                    .antMatchers("/cadastroPaciente/").permitAll().antMatchers("/cadastroMedico/").permitAll()
                    .antMatchers("/cadastroAtendente/").permitAll().antMatchers("/loginAtendente/").permitAll()
                    .antMatchers("/loginMedico/").permitAll().antMatchers("/cadastrarAtendente/")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/cadastrarPaciente")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/cadastrarMedico")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/alterarPaciente")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/alterarMedico")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/").permitAll()// .hasAuthority(Perfil.ADMIN.toString())
                    .antMatchers("/agendarConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                    .antMatchers("/alterarAgendamento").hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/pacientes")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/medicos").hasAuthority(Perfil.ATENDENTE.toString())
                    .antMatchers("/tela").hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/consultas")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/detalhesConsulta")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/deletarAgendamento")
                    .hasAuthority(Perfil.ATENDENTE.toString()).antMatchers("/deletarMedico")
                    .hasAuthority(Perfil.ATENDENTE.toString()).anyRequest().authenticated().and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

            http.formLogin().loginPage("/loginAtendente").defaultSuccessUrl("/tela").permitAll();

        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    
    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsImplService detalhes;

        public App2ConfigurationAdapter() {
            super();
        }

        

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/pageHome").permitAll().antMatchers("/").permitAll().antMatchers("/servicos").permitAll()
                    .antMatchers("/selecionar").permitAll().antMatchers("/loginPaciente").permitAll()
                    .antMatchers("/cadastroPaciente").permitAll().antMatchers("/cadastroAtendente").permitAll()
                    .antMatchers("/loginAtendente").permitAll().antMatchers("/loginMedico").permitAll()
                    .antMatchers("/cadastroMedico").permitAll().antMatchers("/telaPaciente")
                    .hasAuthority(Perfil.PACIENTE.toString()).anyRequest().authenticated().and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

            http.formLogin().loginPage("/loginPaciente").defaultSuccessUrl("/telaPaciente").permitAll();

        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    
    }

    @Configuration
    @Order(3)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsImplService detalhes;

        public App3ConfigurationAdapter() {
            super();
        }

       

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/pageHome").permitAll().antMatchers("/").permitAll().antMatchers("/servicos").permitAll()
                    .antMatchers("/selecionar").permitAll().antMatchers("/loginPaciente").permitAll()
                    .antMatchers("/cadastroPaciente").permitAll().antMatchers("/cadastroAtendente").permitAll()
                    .antMatchers("/loginAtendente").permitAll().antMatchers("/loginMedico").permitAll()
                    .antMatchers("/cadastroMedico").permitAll().antMatchers("/telaMedico")
                    .hasAuthority(Perfil.MEDICO.toString()).anyRequest().authenticated().and().logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

            http.formLogin().loginPage("/loginMedico").defaultSuccessUrl("/telaMedico").permitAll();

        }
       @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    }
} 
}
}


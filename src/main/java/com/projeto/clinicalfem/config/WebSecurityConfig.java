package com.projeto.clinicalfem.config;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.service.UserDetailsImplAtendenteService;
import com.projeto.clinicalfem.service.UserDetailsImplMedicoService;
import com.projeto.clinicalfem.service.UserDetailsImplServicePaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
        private UserDetailsImplAtendenteService detalhes;

        public App1ConfigurationAdapter() {
            super();
        }

        // vamo tentar assim com coisos individuais k

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/pageHome").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/servicos").permitAll()
                .antMatchers("/selecionar").permitAll()
                .antMatchers("/loginPaciente").permitAll()
                .antMatchers("/cadastroPaciente").permitAll()
                .antMatchers("/cadastroAtendente").permitAll()
                .antMatchers("/loginAtendente").permitAll()
                .antMatchers("/loginMedico").permitAll()
                .antMatchers("/cadastroMed").permitAll()
                .antMatchers("/telaMedico").hasAuthority(Perfil.MEDICO.toString())
                .antMatchers("/telaPaciente").hasAuthority(Perfil.PACIENTE.toString())
                .antMatchers("/cadastrarAtendente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/agendarConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/pacientes").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/medicos").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/tela").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/consultas").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/detalhesConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

            http.formLogin().loginPage("/loginAtendente").loginProcessingUrl("/loginAtendente").defaultSuccessUrl("/tela").permitAll().and().csrf().disable();
            http.csrf().disable();

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(detalhes).passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**","/loginMedico","/loginPaciente");
        }
    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsImplServicePaciente paciente;

        public App2ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/pageHome").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/servicos").permitAll()
                .antMatchers("/selecionar").permitAll()
                .antMatchers("/loginPaciente").permitAll()
                .antMatchers("/cadastroPaciente").permitAll()
                .antMatchers("/cadastroAtendente").permitAll()
                .antMatchers("/loginAtendente").permitAll()
                .antMatchers("/loginMedico").permitAll()
                .antMatchers("/cadastroMed").permitAll()
                .antMatchers("/telaMedico").hasAuthority(Perfil.MEDICO.toString())
                .antMatchers("/telaPaciente").hasAuthority(Perfil.PACIENTE.toString())
                .antMatchers("/cadastrarAtendente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/agendarConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/pacientes").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/medicos").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/tela").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/consultas").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/detalhesConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));



            http.formLogin().loginPage("/loginPaciente").defaultSuccessUrl("/telaPaciente").permitAll().and().csrf().disable();
            http.csrf().disable();

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(paciente).passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**","/loginAtendente","/loginMedico");
        }
    }

    @Configuration
    @Order(3)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsImplMedicoService medico;

        public App3ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests() .antMatchers("/pageHome").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/servicos").permitAll()
                .antMatchers("/selecionar").permitAll()
                .antMatchers("/loginPaciente").permitAll()
                .antMatchers("/cadastroPaciente").permitAll()
                .antMatchers("/cadastroAtendente").permitAll()
                .antMatchers("/loginAtendente").permitAll()
                .antMatchers("/loginMedico").permitAll()
                .antMatchers("/cadastroMed").permitAll()
                .antMatchers("/telaMedico").hasAuthority(Perfil.MEDICO.toString())
                .antMatchers("/telaPaciente").hasAuthority(Perfil.PACIENTE.toString())
                .antMatchers("/cadastrarAtendente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/cadastrarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarPaciente").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/agendarConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/alterarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/pacientes").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/medicos").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/tela").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/consultas").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/detalhesConsulta").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarAgendamento").hasAuthority(Perfil.ATENDENTE.toString())
                .antMatchers("/deletarMedico").hasAuthority(Perfil.ATENDENTE.toString())
                .anyRequest().authenticated().and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

            http.formLogin().loginPage("/loginMedico").defaultSuccessUrl("/telaMedico").permitAll().and().csrf().disable();
            http.csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(medico).passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**","/loginAtendente","/loginPaciente");
        }
    }
}

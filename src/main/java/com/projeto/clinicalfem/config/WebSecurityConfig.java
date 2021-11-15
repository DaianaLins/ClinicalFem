package com.projeto.clinicalfem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.projeto.clinicalfem.enums.Perfil;
import com.projeto.clinicalfem.service.UserDetailsImplAtendenteService;
import com.projeto.clinicalfem.service.UserDetailsImplMedicoService;
import com.projeto.clinicalfem.service.UserDetailsImplServicePaciente;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	

	 
    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

    	 @Autowired
         private UserDetailsImplAtendenteService detalhes;

         @Bean
     	public BCryptPasswordEncoder passwordEncoder() {
     		return new BCryptPasswordEncoder();
     	}
         
      
         @Autowired
         public void configure(AuthenticationManagerBuilder auth) throws Exception {
     		auth
                     .userDetailsService(detalhes)
                     
                     .passwordEncoder(new BCryptPasswordEncoder());
     		
         }
     	

         // vamo tentar assim com coisos individuais k
        

             
         
         @Override
         protected void configure(HttpSecurity http) throws Exception {
        	
        	 http
             .requestMatcher(new AntPathRequestMatcher("/atendente/**"))
             .csrf().disable()      
             .authorizeRequests().antMatchers("/atendente/").anonymous()
             .antMatchers("/atendente/tela").hasAuthority(Perfil.ATENDENTE.toString())
             .and().formLogin().loginPage("/atendente/login").defaultSuccessUrl("/atendente/tela").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/pageHome");
        	 

         }

     
     	
         @Override
         public void configure(WebSecurity web) throws Exception {
             web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**");
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
        	 http
              .requestMatcher(new AntPathRequestMatcher("/medico/**"))
              .csrf().disable()   
                 .authorizeRequests().antMatchers("/atendente/").anonymous()
                 .antMatchers("/atendente/tela").hasAuthority(Perfil.ATENDENTE.toString())
                 .and().formLogin().loginPage("/medico/login").defaultSuccessUrl("/medico/telaMedico").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/pageHome");
       	 /*
       	   http.antMatcher("/user*")
              .authorizeRequests()
              .anyRequest()
              .hasAuthority(Perfil.ATENDENTE.toString())
              
              .and()
              .formLogin()
              .loginPage("/loginAtendente")
              .loginProcessingUrl("/user_login")
              .failureUrl("/loginUser?error=loginError")
              .defaultSuccessUrl("/tela")
              
              .and()
              .logout()
              .logoutUrl("/user_logout")
              .logoutSuccessUrl("/protectedLinks")
              
              
              .and()
              .exceptionHandling()
              .accessDeniedPage("/403")
              
              .and()
              .csrf().disable();
        
       	   	*/
        }

        @Autowired
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(medico).passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**");
        }
    }
    
    @Configuration
    @Order(4)
    public static class App3ConfigurationAdapter2 extends WebSecurityConfigurerAdapter {
    	 @Autowired
         private UserDetailsImplServicePaciente paciente;

        public App3ConfigurationAdapter2() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
       
       	 http
            .requestMatcher(new AntPathRequestMatcher("/paciente/**"))
            .csrf().disable()      
            .authorizeRequests().antMatchers("/paciente/").anonymous()
           
            .and().formLogin().loginPage("/paciente/login").defaultSuccessUrl("/paciente/telaPaciente").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/pageHome");
        
       	 /*
       	   http.antMatcher("/user*")
              .authorizeRequests()
              .anyRequest()
              .hasAuthority(Perfil.ATENDENTE.toString())
              
              .and()
              .formLogin()
              .loginPage("/loginAtendente")
              .loginProcessingUrl("/user_login")
              .failureUrl("/loginUser?error=loginError")
              .defaultSuccessUrl("/tela")
              
              .and()
              .logout()
              .logoutUrl("/user_logout")
              .logoutSuccessUrl("/protectedLinks")
              
              
              .and()
              .exceptionHandling()
              .accessDeniedPage("/403")
              
              .and()
              .csrf().disable();
        
       	   	*/
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(paciente).passwordEncoder(new BCryptPasswordEncoder());
        }
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/materialize/**", "/static/**", "/bootstrap/**", "/style/**");
        }
    }


   
}
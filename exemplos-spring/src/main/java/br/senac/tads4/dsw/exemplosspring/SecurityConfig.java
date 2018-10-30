/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author fernando.tsuda
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static PasswordEncoder plainPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence cs) {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String hashSenha) {
                return hashSenha.equals(cs.toString());
            }
        };
    }
    
    public static PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bcryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
                    .antMatchers("/protegido/peao").hasRole("PEAO")
                    .antMatchers("/protegido/fodon").hasRole("FODON")
                    .antMatchers("/protegido/god").hasRole("GOD")
                    .antMatchers("/**").authenticated()
            .and()
                .formLogin()
                    .loginPage("/login") // DEFINE QUE A TELA TEM Q SER IMPLEMENTADA
                    .usernameParameter("username")
                    .passwordParameter("senha")
                    .defaultSuccessUrl("/home").permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");
                

    }

}

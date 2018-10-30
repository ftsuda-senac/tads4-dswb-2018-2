/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.model;

import br.senac.tads4.dsw.exemplosspring.SecurityConfig;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author fernando.tsuda
 */
public class UsuarioSistema implements UserDetails {
    
    private String username;
    
    private String nomeCompleto;
    
    private String hashSenha;
    
    private List<Papel> papeis;
    
    public UsuarioSistema() { }
    
    public UsuarioSistema(String username, String nomeCompleto, String senhaAberta, List<Papel> papeis) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        setSenha(senhaAberta);
        this.papeis = papeis;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }
    
    public void setSenha(String senhaAberta) {
        this.hashSenha = 
                SecurityConfig.bcryptPasswordEncoder().encode(senhaAberta);
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    @Override
    //public Collection<? extends GrantedAuthority> getAuthorities() {
    public List<Papel> getAuthorities() {
        return getPapeis();
    }

    @Override
    public String getPassword() {
        return getHashSenha();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

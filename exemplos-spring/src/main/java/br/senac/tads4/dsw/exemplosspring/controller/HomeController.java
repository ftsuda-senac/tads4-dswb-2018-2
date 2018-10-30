/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.UsuarioSistema;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    // Obtido em https://www.baeldung.com/get-user-in-spring-security
    public ModelAndView mostrarHome(Authentication authentication) {
        UsuarioSistema usuario = (UsuarioSistema) authentication.getPrincipal();
        return new ModelAndView("home")
                .addObject("usuario", usuario);
    }

}

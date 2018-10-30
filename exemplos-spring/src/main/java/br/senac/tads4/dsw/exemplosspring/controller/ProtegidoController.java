/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/protegido")
public class ProtegidoController {

    @GetMapping("/peao")
    public ModelAndView mostrarPeaoPage() {
        return new ModelAndView("resultado")
                .addObject("titulo", "Página do PEAO")
                .addObject("msg", "Usuário logado possui papel \"PEAO\"");
    }

    @GetMapping("/fodon")
    public ModelAndView mostrarFodonPage() {
        return new ModelAndView("resultado")
                .addObject("titulo", "Página do FODON")
                .addObject("msg", "Usuário logado possui papel \"FODON\"");
    }

    @GetMapping("/god")
    public ModelAndView mostrargODPage() {
        return new ModelAndView("resultado")
                .addObject("titulo", "Página do GOD")
                .addObject("msg", "Usuário logado possui papel \"GOD\"");
    }

}

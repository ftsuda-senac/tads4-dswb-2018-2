/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Produto;
import br.senac.tads4.dsw.exemplosspring.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; //"equivale" a new ProdutoServiceJPA();

    @GetMapping
    public ModelAndView listar(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "quantidade", defaultValue = "100") int quantidade
            ) {
        List<Produto> produtos = produtoService.findAll(offset, quantidade);
        return new ModelAndView("produto/lista")
                .addObject("produtos", produtos);
    }

    @GetMapping("/novo")
    public ModelAndView incluirNovo() {
        return new ModelAndView("produto/formulario")
                .addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Produto produto = produtoService.findById(id);
        return new ModelAndView("produto/formulario")
                .addObject("produto", produto);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("produto") Produto produto, 
            RedirectAttributes redirectAttributes) {
        produto = produtoService.save(produto);
        redirectAttributes.addFlashAttribute(
                "msg", "Produto salvo com sucesso");
        return new ModelAndView("redirect:/produto");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.model.Categoria;
import br.senac.tads4.dsw.exemplosspring.model.Produto;
import br.senac.tads4.dsw.exemplosspring.service.CategoriaService;
import br.senac.tads4.dsw.exemplosspring.service.ProdutoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView listar(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "quantidade", defaultValue = "100") int quantidade
            ) {
        //List<Produto> produtos = produtoService.findAll(offset, quantidade);
        List<Produto> produtos = produtoService.findAll();
        return new ModelAndView("produto/lista-bs4")
                .addObject("produtos", produtos);
    }
    @GetMapping("/por-nome")
    public ModelAndView listar2(
            @RequestParam(name = "nome") String nome
            ) {
        //List<Produto> produtos = produtoService.findAll(offset, quantidade);
        List<Produto> produtos = produtoService.findByNome(nome);
        return new ModelAndView("produto/lista-bs4")
                .addObject("produtos", produtos);
    }

    @GetMapping("/novo")
    public ModelAndView incluirNovo() {
        return new ModelAndView("produto/formulario-bs4")
                .addObject("produto", new Produto());
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Produto> optProduto = produtoService.findById(id);
        Produto produto = optProduto.get();
        if (produto.getCategorias() != null && 
                !produto.getCategorias().isEmpty()) {
            
            Set<Integer> idsCategorias = new HashSet<Integer>();
            for (Categoria c : produto.getCategorias()) {
                idsCategorias.add(c.getId());
            }
            produto.setIdsCategorias(idsCategorias);
        }
        return new ModelAndView("produto/formulario-bs4")
                .addObject("produto", produto);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("produto") Produto produto, 
            RedirectAttributes redirectAttributes) {
        if (produto.getIdsCategorias() != null && 
                !produto.getIdsCategorias().isEmpty()) {
            Set<Categoria> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : produto.getIdsCategorias()) {
                Optional<Categoria> optCat = categoriaService.findById(idCat);
                Categoria cat = optCat.get();
                cat.setProdutos(new HashSet<Produto>(Arrays.asList(produto)));
                categoriasSelecionadas.add(cat);
            }
            produto.setCategorias(categoriasSelecionadas);
        }
        produto = produtoService.save(produto);
        redirectAttributes.addFlashAttribute(
                "msg", "Produto salvo com sucesso");
        return new ModelAndView("redirect:/produto");
    }
    
    @ModelAttribute("categorias")
    public List<Categoria> listaCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return categorias;
    }

}

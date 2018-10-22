/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;

import br.senac.tads4.dsw.exemplosspring.model.Categoria;
import java.util.List;

/**
 *
 * @author fernando.tsuda
 */
public interface CategoriaService {
    
    List<Categoria> findAll();
    
    Categoria findById(int id);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.service;

import br.senac.tads4.dsw.exemplosspring.model.Produto;
import java.util.List;

/**
 *
 * @author fernando.tsuda
 */
public interface ProdutoService {
    
    List<Produto> findAll(int offset, int quantity);
    
    Produto findById(long idProduto);
    
    Produto save(Produto produto);
    
}

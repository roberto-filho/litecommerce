package org.filho.litecommerce.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.helpers.ProdutoComPreco;
import org.filho.litecommerce.model.CarrinhoCompras;
import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrinho")
@Scope("session")
public class CarrinhoController {
  
  @Autowired CarrinhoCompras carrinho;
  @Autowired ProdutoRepository produtoRepo;

  /**
   * Adiciona um produto no carrinh ode compras
   * @param produto o produto a ser adicionado
   * @param model 
   * @return
   */
  @RequestMapping("/adicionar/{id}")
  public String adicionarProduto(@PathVariable("id") Produto produto) {
    // TODO Adicionar o produto no carrinho da sessão do usuário
    
    carrinho.addProduto(produto);
    
    //model.addAttribute("name", name);
    return "redirect:/produtos";
  }

  @RequestMapping
  public String listar(Model model) {
    // Buscar todos os produtos no carrinho e mostrá-los
    HashMap<Produto, Integer> produtosQt = carrinho.getProdutos();
    
    List<ProdutoComPreco> precos = produtoRepo.calcularPrecos(carrinho.getProdutosList());
    
    BigDecimal total = BigDecimal.ZERO;
    
    for (ProdutoComPreco produtoComPreco : precos) {
      // Somar os preços
      /// TODO Multiplicar pela quantidade
      total = total.add(produtoComPreco.getPreco());
    }
    // Adiciona no model
    model.addAttribute("total", total);
    
    return "carrinho/index";
  }


}
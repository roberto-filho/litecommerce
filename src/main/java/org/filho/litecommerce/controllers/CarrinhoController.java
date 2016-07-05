package org.filho.litecommerce.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.data.custom.ProdutoRepositoryCustom;
import org.filho.litecommerce.model.CarrinhoCompras;
import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.model.ProdutoComPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;

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
    // Adicionar o produto no carrinho da sessão do usuário
    
    carrinho.addProduto(produto);
    
    //model.addAttribute("name", name);
    return "redirect:/produtos";
  }
  
  @RequestMapping("/quantidade/{id}")
  public String setQuantidade(@PathVariable("id") Produto produto, @RequestParam String qtd) {
    // Não fiz nenhuma verificação aqui.
    carrinho.setQuantidade(produto, Integer.valueOf(qtd));
    
    //model.addAttribute("name", name);
    return "redirect:/carrinho";
  }

  @RequestMapping
  public String listar(Model model) {
    // Buscar todos os produtos no carrinho e mostrá-los
    HashMap<Produto, Integer> produtosQt = carrinho.getProdutos();
    
    List<ProdutoComPreco> precos = produtoRepo.calcularPrecos(carrinho.getProdutosList());
    
    // Cria um map para os produtos/precos
    Map<Produto, BigDecimal> produtoPrecos = Maps.newHashMap();
    
    BigDecimal total = BigDecimal.ZERO;
    
    for (ProdutoComPreco produtoComPreco : precos) {
      BigDecimal preco = produtoComPreco.getPreco();
      // Multiplicar pela quantidade
      int quantidade = produtosQt.get(produtoComPreco.getProduto());
      preco = preco.multiply(new BigDecimal(quantidade), ProdutoRepositoryCustom.MATH_CONTEXT);
      
      // Adiciona no map
      produtoPrecos.put(produtoComPreco.getProduto(), preco);
      
      // Somar os preços
      total = total.add(preco);
    }
    
    // Adiciona no model
    model.addAttribute("total", total);
    model.addAttribute("produtos", produtoPrecos);
    
    return "carrinho/index";
  }


}
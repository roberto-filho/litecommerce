package org.filho.litecommerce.controllers;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

  @Autowired
  private ProdutoRepository service;

  @RequestMapping("/greeting")
  public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
    model.addAttribute("name", name);
    return "greeting";
  }

  @RequestMapping("/")
  public String home(Model model, HttpSession session) {
    String visitor = (String) session.getAttribute("visitor");
    
    if(visitor == null)
      session.setAttribute("visitor", "false");
    
    model.addAttribute("visitor", visitor == null);
    
    return "index";
  }

  @RequestMapping("/populate")
  public String populate(Model model) {
    // Só gera um objeto padrão
    Produto produto = new Produto();
    produto.setNome("Chinelo");
    produto.setValorCustoCompra(BigDecimal.TEN);

    service.save(produto);

    model.addAttribute("nome", produto.getNome());

    return "redirect:/produtos";
  }
  
  /**
   * Remove todos os produtos cadastrados.
   * @return 
   */
  @RequestMapping("/reset")
  public String resetProdutos() {
    // Remove todos os produtos cadastrados
    Iterable<Produto> todos = service.findAll();
    service.delete(todos);
    
    return "redirect:/produtos";
  }

}
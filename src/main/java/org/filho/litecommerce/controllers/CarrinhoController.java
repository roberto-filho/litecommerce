package org.filho.litecommerce.controllers;

import javax.servlet.http.HttpSession;

import org.filho.litecommerce.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

  /**
   * Adiciona um produto no carrinh ode compras
   * @param produto o produto a ser adicionado
   * @param model 
   * @return
   */
  @RequestMapping("/adicionar/{id}")
  public String greeting(@PathVariable("id") Produto produto, HttpSession session) {
    // TODO Adicionar o produto no carrinho da sessão do usuário
    
    
    //model.addAttribute("name", name);
    return "greeting";
  }

  @RequestMapping("/")
  public String listar(Model model, HttpSession session) {
    String visitor = (String) session.getAttribute("visitor");
    
    if(visitor == null)
      session.setAttribute("visitor", "false");
    
    model.addAttribute("visitor", visitor == null);
    
    return "index";
  }


}
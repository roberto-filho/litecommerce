package org.filho.litecommerce.controllers;

import java.math.BigDecimal;

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
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @RequestMapping("/home")
    public String home() {
    	return "index";
    }
    
    @RequestMapping("/populate")
    public String populate(Model model) {
    	
    	Produto produto = new Produto();
    	produto.setNome("Chinelo");
    	produto.setPrice(BigDecimal.TEN);
    	
    	service.save(produto);
    	
    	model.addAttribute("nome", produto.getNome());
    	
    	return "show";
    }

}
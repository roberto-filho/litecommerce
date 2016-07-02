package org.filho.litecommerce.controllers;

import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository repo;
	
	@RequestMapping
	public String list(Model model) {
		// Show a list of the models
		
		model.addAttribute("produtos", repo.findAll());
		
		return "list";
	}
	
	@RequestMapping("/new")
	public String create() {
	  return "new";
	}
	
	@RequestMapping("/{id}")
	public String showProduto(@PathVariable("id") Produto produto, Model model) {
		model.addAttribute("produto", produto);
		
		return "show";
	}
}

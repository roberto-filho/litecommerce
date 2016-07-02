package org.filho.litecommerce.controllers;

import java.math.BigDecimal;

import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.model.ParametroLoja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Iterables;

@Controller
@RequestMapping("/parametros")
public class ParametroLojaController {

  @Autowired
  private ParametroLojaRepository lojaDao;

  @RequestMapping
  public String edit(Model model) {
    // Se não tem nenhum parâmetro ainda, cria um novo
    ParametroLoja parametro = Iterables.getFirst(lojaDao.findAll(), new ParametroLoja());
    
    model.addAttribute("parametro", parametro);
    
    return "parametro/edit";
  }

  @RequestMapping("save")
  public String saveParametros(
      BigDecimal valorTotalDespesas,
      BigDecimal valorMargemLucro) {
    // TODO Salva o parâmetro (possivelmente excluindo o antigo?)
    
    
    
    return "redirect:";
  }

}
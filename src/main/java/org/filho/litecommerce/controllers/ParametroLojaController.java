package org.filho.litecommerce.controllers;

import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.model.ParametroLoja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/parametros")
public class ParametroLojaController {

  @Autowired private ParametroLojaRepository lojaRespository;

  @RequestMapping
  public String edit(Model model) {
    model.addAttribute("parametro", lojaRespository.buscarUnico());
    
    return "parametro/edit";
  }

  /**
   * Salva o parâmetro.
   * @param parametro os dados do parâmetro.
   * @param att para usar redirect att.
   * @return
   */
  @RequestMapping("save")
  public String saveParametros(ParametroLoja parametro, RedirectAttributes att) {
    // Buscar o único parâmetro cadastrado
    ParametroLoja parametroBanco = lojaRespository.buscarUnico();
    
    // TODO Validar valores maiores que zero (exceto margem de lucro)
    
    // Atualiza o parametro salvo e salva ele.
    lojaRespository.save(parametroBanco.atualizar(parametro));
    // Atualiza a página
    return "redirect:/parametros";
  }
  
  @RequestMapping("reset")
  public String limpar() {
    lojaRespository.deleteAll();
    return "redirect:";
  }

}
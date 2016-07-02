package org.filho.litecommerce.controllers;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  ProdutoRepository repo;

  @RequestMapping
  public String list(Model model) {
    // Show a list of the models

    model.addAttribute("produtos", repo.findAll());

    return "produto/list";
  }

  @RequestMapping(value = "create", /*headers="content-type=multipart/*", */method=RequestMethod.POST)
  public String create(Produto produto) {
    // Primeiro validamos a imagem.
//    byte[] foto = produto.getFoto();
//    if(foto != null && foto.length > 0)
//      validarImagem(foto);
    

    // Salva
    repo.save(produto);
    
    return "redirect:/produtos";
  }

  @RequestMapping("new")
  public String newPerson(Model model) {
    // Cria um novo objeto para inserção no banco
    model.addAttribute("produto", new Produto());

    return "produto/new";
  }
  
  @RequestMapping("foto/{id}")
  @ResponseBody
  public String renderizarFoto(@PathVariable("id") Produto produto) {
    byte[] t = Base64.encodeBase64(produto.getFoto());
    try {
      return "data:image/jpeg;base64,"+new String(t, "utf-8");
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

//  @RequestMapping("{id}")
//  public String showProduto(@PathVariable("id") Produto produto, Model model) {
//    model.addAttribute("produto", produto);
//
//    return "produto/show";
//  }

  /**
   * Verifica se a imagem é um JPEG e tem o tamanho 50x50.
   * @param image a imagem a ser validada
   */
  private void validarImagem(MultipartFile image) {
    if (!image.getContentType().equals("image/jpeg")) {
      throw new RuntimeException("Favor informar uma imagem JPG");
    }
  }
  
  
}

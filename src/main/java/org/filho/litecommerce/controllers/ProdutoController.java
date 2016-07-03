package org.filho.litecommerce.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  ProdutoRepository repo;
  
  private Log logger = LogFactory.getLog(getClass());

  @RequestMapping
  public String list(Model model) {
    // Show a list of the models

    model.addAttribute("produtos", repo.findAll());

    return "produto/list";
  }

  @RequestMapping(value = "create", /*headers="content-type=multipart/*", */method=RequestMethod.POST)
  public String create(
      @RequestParam String nome,
      @RequestParam String descricao,
      @RequestParam String valorCustoCompra,
      @RequestParam MultipartFile foto) {
    // Primeiro validamos a imagem.
//    byte[] foto = produto.getFoto();
//    if(foto != null && foto.length > 0)
    validarImagem(foto);
    
    Produto p = new Produto();
    p.setDescricao(descricao);
    p.setNome(nome);
    try {
      p.setFoto(foto.getBytes());
    } catch (IOException e) {
      logger.error("Impossível salvar a foto", e);
    }

    // Salva
    repo.save(p);
    
    return "redirect:/produtos";
  }

  @RequestMapping("new")
  public String newPerson(Model model) {
    // Cria um novo objeto para inserção no banco
    model.addAttribute("produto", new Produto());

    return "produto/new";
  }
  
  @ResponseBody
  @RequestMapping(value="foto/{idProduto}", method = RequestMethod.GET)
  public String renderizarFoto(@PathVariable("idProduto") Produto produto) {
    byte[] fotoBytes = Base64.encodeBase64(produto.getFoto());
    // Se não tem foto
    if(fotoBytes == null || fotoBytes.length < 1)
      return "";
    
    String encodedImage = "";
    try {
      encodedImage = new String(fotoBytes, "utf-8");
    } catch (UnsupportedEncodingException e) {
      logger.error("Erro ao criar um string base64", e);
    }
    
      return "data:image/jpeg;base64,"+encodedImage;
  }
  
  @RequestMapping(value = "/foto", method = RequestMethod.GET)
  public void showImage(@RequestParam("id") int produtoId, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
    Produto p = repo.findOne(new Long(produtoId));
    
    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
    response.getOutputStream().write(p.getFoto());

    response.getOutputStream().close();
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
    // TODO Validar se a imagem tem o tamanho de 50x50px
  }
  
  
}

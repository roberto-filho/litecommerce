package org.filho.litecommerce.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.CarrinhoCompras;
import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.model.ProdutoComPreco;
import org.filho.litecommerce.specifications.ProdutoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("session")
public class ProdutoController {

  @Autowired ProdutoRepository produtoRepo;
  @Autowired ParametroLojaRepository lojaRepo;
  @Autowired CarrinhoCompras carrinho;
  
  private Log logger = LogFactory.getLog(getClass());

  @RequestMapping
  public String list(Model model) {
    if(carrinho == null)
      carrinho = new CarrinhoCompras();
    // Busca os produtos que têm custo, pois os que não tem custo não são mostrados na lista
    List<Produto> produtosComCusto = produtoRepo.findAll(ProdutoSpecifications.produtosComCusto());
    // Busca o preço dos produtos com custo
    List<ProdutoComPreco> comPreco = produtoRepo.calcularPrecos(produtosComCusto);
    
    model.addAttribute("produtos", comPreco);
    model.addAttribute("carrinho", carrinho);

    return "produto/list";
  }

  @RequestMapping(value = "create", /*headers="content-type=multipart/*", */method=RequestMethod.POST)
  public String create(
      @RequestParam String nome,
      @RequestParam String descricao,
      @RequestParam BigDecimal valorCustoCompra,
      @RequestParam MultipartFile foto) {
    // Primeiro validamos a imagem.
    validarImagem(foto);
    
    // Cria o objeto a ser salvo
    Produto p = new Produto();
    p.setDescricao(descricao);
    p.setNome(nome);
    p.setValorCustoCompra(valorCustoCompra); // TODO Deve ser maior que zero
    // Joga a imagem no objeto
    try {
      p.setFoto(foto.getBytes());
    } catch (IOException e) {
      logger.error("Erro ao salvar a foto", e);
    }

    // Salva
    produtoRepo.save(p);
    
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

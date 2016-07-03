package org.filho.litecommerce.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.helpers.ProdutoComPreco;
import org.filho.litecommerce.model.ParametroLoja;
import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.specifications.ProdutoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired ProdutoRepository produtoRepo;
  @Autowired ParametroLojaRepository lojaRepo;
  
  private Log logger = LogFactory.getLog(getClass());
  private static final MathContext MATH_CONTEXT = new MathContext(20, RoundingMode.DOWN);

  @RequestMapping
  public String list(Model model) {
    // Busca o número de produtos cadastrados para realizar o rateio do preço
    int numeroProdutosCadastrados = Lists.newArrayList(produtoRepo.findAll()).size();
    
    // Busca os parâmetros da loja, onde estão informados valores
    ParametroLoja parametro = lojaRepo.buscarUnico();
    
    // Rateia os custos (com 20 casas decimais)
    BigDecimal valorRateioCustos = parametro.getValorTotalDespesas().divide(new BigDecimal(numeroProdutosCadastrados), MATH_CONTEXT);
    
    // Faz a matemática da margem de lucro
    BigDecimal margemLucro = parametro.getValorMargemLucro();
    
    // Se a margem de lucro for menor que zero, usa 0
    if(margemLucro.signum() < 0)
      margemLucro = new BigDecimal(0);
    
    // Transforma o lucro na representação decimal da porcentagem
    margemLucro = margemLucro.divide(new BigDecimal(100L), MATH_CONTEXT);
    
    // Busca os produtos que têm custo, pois os que não tem custo não são mostrados na lista
    Iterable<Produto> produtos = produtoRepo.findAll(ProdutoSpecifications.produtosComCusto());
    
    // Cria a lista que será passada para a view
    List<ProdutoComPreco> comPreco = Lists.newArrayList();
    
    // Calcular o preço de cada produto
    for (Produto produto : produtos) {
      // Adiciona os valores
      BigDecimal preco = produto.getValorCustoCompra().add(valorRateioCustos).setScale(2, RoundingMode.HALF_EVEN);
      
      // Aplica a margem de lucro e arredonda para 2 casas
      BigDecimal valorLucro = preco.multiply(margemLucro).setScale(2, RoundingMode.HALF_EVEN);
      preco = preco.add(valorLucro);
      
      comPreco.add(new ProdutoComPreco(produto, preco));
    }
    model.addAttribute("produtos", comPreco);

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
    p.setValorCustoCompra(valorCustoCompra);
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
  
  @RequestMapping(value = "/foto", method = RequestMethod.GET)
  public void showImage(@RequestParam("id") int produtoId, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{
    Produto p = produtoRepo.findOne(new Long(produtoId));
    
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

package org.filho.litecommerce.data.custom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.ParametroLoja;
import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.model.ProdutoComPreco;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom {

  @Autowired ParametroLojaRepository lojaRepo;
  @Autowired ProdutoRepository produtoRepo;
  
  @Override
  public List<ProdutoComPreco> calcularPrecos(Iterable<Produto> produtos) {
    // Busca o número de produtos cadastrados para realizar o rateio do preço
    int numeroProdutosCadastrados = Lists.newArrayList(produtoRepo.findAll()).size();
    
    // Para não dar divisão por 0
    if (numeroProdutosCadastrados == 0)
      numeroProdutosCadastrados = 1;
    
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
    
    return comPreco;
  }

}

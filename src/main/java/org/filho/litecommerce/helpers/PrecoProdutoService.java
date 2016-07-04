package org.filho.litecommerce.helpers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.filho.litecommerce.data.ProdutoRepository;
import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.model.ProdutoComPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;

/**
 * Bean que calcula o preço de um Produto somente.
 * @author Roberto Filho
 */
@Service("precoProduto")
public class PrecoProdutoService {
  
  @Autowired
  private ProdutoRepository produtoRepo;

  public String preco(Produto produto) {
    // Busca o preço desse produto em específico
    List<ProdutoComPreco> precos = produtoRepo.calcularPrecos(Collections.singletonList(produto));
    
    // Pega o único produto
    ProdutoComPreco preco = Iterables.getFirst(precos, null);
    // Retorna o preço, ou zero
    return preco != null ? preco.getPreco().toPlainString() : BigDecimal.ZERO.toPlainString();
  }
}

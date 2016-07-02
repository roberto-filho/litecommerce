package org.filho.litecommerce.model;

import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Maps;

/**
 * Representa o carrinho de compras do usuário. Essa classe
 * tem um escopo de sessão, ou seja, cada sessão terá seu próprio
 * carrinho.
 * @author Roberto Filho
 *
 */
@Scope("session")
public class CarrinhoCompras {
  private Map<Produto, Integer> produtos = Maps.newHashMap();
  
  /**
   * Adiciona um produto no carrinho.
   * @param produto o produto a ser adicionado
   * @return a quantidade desse produto que o carrinho possui.
   */
  public int addProduto(Produto produto) {
    // Adicionar o produto no carrinho
    Integer quantidade = produtos.get(produto);
    
    // Se não buscou quantidade, não exise o produto no carrinho
    if(quantidade == null) {
      quantidade = 1;
    } else
      quantidade++;
    
    return produtos.put(produto, quantidade);
  }
  
}

package org.filho.litecommerce.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Representa o carrinho de compras do usuário. Essa classe
 * tem um escopo de sessão, ou seja, cada sessão terá seu próprio
 * carrinho.
 * @author Roberto Filho
 *
 */
@Component("carrinho")
@Scope("session")
public class CarrinhoCompras implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private Map<Produto, Integer> produtos = Maps.newHashMap();
  
  /**
   * Adiciona um produto no carrinho.
   * @param produto o produto a ser adicionado
   * @return a quantidade desse produto que o carrinho possui.
   */
  public void addProduto(Produto produto) {
    // Adicionar o produto no carrinho
    Integer quantidade = produtos.get(produto);
    
    // Se não buscou quantidade, não exise o produto no carrinho
    if(quantidade == null) {
      quantidade = 1;
    } else
      quantidade++;
    
    // Adiciona nos produtos
    produtos.put(produto, quantidade);
  }
  
  public int quantidadeProduto(Produto p) {
    return produtos.getOrDefault(p, 0);
  }
  
  /**
   * Seta a quantidade de um dado produto no carrinho.
   * @param produto o produto a ter a sua quantidade alterada
   * @param qtd a nova quantidade
   */
  public void setQuantidade(Produto produto, int qtd) {
    produtos.put(produto, qtd);
  }
  
  /**
   * Retorna uma cópia dos produtos deste carrinho para
   * não permitir edição do carrinho diretamente.
   * @return os produtos deste carrinho
   */
  public HashMap<Produto, Integer> getProdutos() {
    return Maps.newHashMap(produtos);
  }
  
  public List<Produto> getProdutosList() {
    return Lists.newArrayList(produtos.keySet());
  }
  
}

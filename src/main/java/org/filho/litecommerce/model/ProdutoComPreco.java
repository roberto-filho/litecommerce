package org.filho.litecommerce.model;

import java.math.BigDecimal;

/**
 * Esse VO foi criado para guardar o preço calculado de algum
 * produto.
 * @author Roberto Filho
 *
 */
public class ProdutoComPreco {
  private Produto produto;
  private BigDecimal preco;

  public ProdutoComPreco(Produto produto, BigDecimal preco) {
    super();
    this.produto = produto;
    this.preco = preco;
  }

  public Produto getProduto() {
    return produto;
  }

  public BigDecimal getPreco() {
    return preco;
  }
}

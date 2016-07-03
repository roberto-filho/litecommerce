package org.filho.litecommerce.helpers;

import java.math.BigDecimal;

import org.filho.litecommerce.model.Produto;

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

package org.filho.litecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nome;
  private BigDecimal custoCompra = BigDecimal.ZERO;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getCustoCompra() {
    return custoCompra;
  }

  public void setCustoCompra(BigDecimal custoCompra) {
    this.custoCompra = custoCompra;
  }

}

package org.filho.litecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParametroLoja {
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private BigDecimal valorTotalDespesas = new BigDecimal(400L);
  private BigDecimal valorMargemLucro = BigDecimal.ZERO;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValorTotalDespesas() {
    return valorTotalDespesas;
  }

  public void setValorTotalDespesas(BigDecimal valorTotalDespesas) {
    this.valorTotalDespesas = valorTotalDespesas;
  }

  public BigDecimal getValorMargemLucro() {
    return valorMargemLucro;
  }

  public void setValorMargemLucro(BigDecimal valorMargemLucro) {
    this.valorMargemLucro = valorMargemLucro;
  }
}

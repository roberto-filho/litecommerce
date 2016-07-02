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
  private String descricao;
  private BigDecimal valorCustoCompra = BigDecimal.ZERO;
  private byte[] foto;

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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public BigDecimal getValorCustoCompra() {
    return valorCustoCompra;
  }

  public void setValorCustoCompra(BigDecimal valorCustoCompra) {
    this.valorCustoCompra = valorCustoCompra;
  }

}

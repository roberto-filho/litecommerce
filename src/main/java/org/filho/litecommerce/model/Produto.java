package org.filho.litecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Size(min=10, max=50, message="Favor informar um nome de até 50 caracteres")
  private String nome;
  
  @Size(min=10, max=200, message="Favor informar uma descrição de até 50 caracteres")
  private String descricao;
  
  @NotEmpty
  private BigDecimal valorCustoCompra = BigDecimal.ZERO;
  
  @Column(columnDefinition="blob")
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
  
  /**
   * Transforma o atributo foto {@code (byte[])} em uma string base64 .
   * @return o valor base64 da foto desse produto.
   */
  public String getFotoBase64encoded() {
    return Base64.encodeBase64String(foto);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Produto other = (Produto) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}

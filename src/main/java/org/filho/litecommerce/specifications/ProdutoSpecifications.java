package org.filho.litecommerce.specifications;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.filho.litecommerce.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecifications {
  
  /**
   * Cria uma especificação para os produtos com custo maior que 0.
   * @return a especificação.
   */
  public static Specification<Produto> produtosComCusto() {
    return new Specification<Produto>() {
      @Override
      public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> criteria, CriteriaBuilder builder) {
        return builder.greaterThan(root.get("valorCustoCompra"), BigDecimal.ZERO);
      }
    };
  }
  
}

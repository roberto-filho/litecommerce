package org.filho.litecommerce.data.custom;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.filho.litecommerce.model.Produto;
import org.filho.litecommerce.model.ProdutoComPreco;

public interface ProdutoRepositoryCustom {
  List<ProdutoComPreco> calcularPrecos(Iterable<Produto> iterable);
  
  MathContext MATH_CONTEXT = new MathContext(20, RoundingMode.DOWN);
}

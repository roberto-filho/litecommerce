package org.filho.litecommerce.data.custom;

import java.util.List;

import org.filho.litecommerce.helpers.ProdutoComPreco;
import org.filho.litecommerce.model.Produto;

public interface ProdutoRepositoryCustom {
  List<ProdutoComPreco> calcularPrecos(Iterable<Produto> iterable);
}

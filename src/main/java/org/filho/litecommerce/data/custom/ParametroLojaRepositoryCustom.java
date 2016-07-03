package org.filho.litecommerce.data.custom;

import org.filho.litecommerce.model.ParametroLoja;

public interface ParametroLojaRepositoryCustom {
  /**
   * Busca o parâmetro único da empresa
   * @return o parâmetro
   */
  ParametroLoja buscarUnico();
}

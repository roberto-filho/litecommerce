package org.filho.litecommerce.data;

import org.filho.litecommerce.data.custom.ParametroLojaRepositoryCustom;
import org.filho.litecommerce.model.ParametroLoja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ParametroLojaRepository extends CrudRepository<ParametroLoja, Long>, ParametroLojaRepositoryCustom {
  // The rest is injected
}

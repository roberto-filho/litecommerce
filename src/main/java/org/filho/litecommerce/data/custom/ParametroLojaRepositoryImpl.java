package org.filho.litecommerce.data.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.filho.litecommerce.model.ParametroLoja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Iterables;

/**
 * Implementa os métodos personalizados. Para o spring data
 * pegar o repositório automaticamente o nome deve ser NomeBeanRepositoryImpl.
 * @author Roberto Filho
 *
 */
@Repository
public class ParametroLojaRepositoryImpl implements ParametroLojaRepositoryCustom {

  @Autowired
  EntityManager em;
  
  @Override
  @Transactional
  public ParametroLoja buscarUnico() {
    // Busca todos os parâmetros
    List<ParametroLoja> all = em.createQuery("from ParametroLoja", ParametroLoja.class).getResultList();
    // Retorna só o primeiro
    ParametroLoja param = Iterables.getFirst(all, null);
    
    if(param == null) {
      param = em.merge(new ParametroLoja());
    }
    
    return param;
  }

}

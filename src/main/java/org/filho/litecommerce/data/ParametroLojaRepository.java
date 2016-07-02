package org.filho.litecommerce.data;

import org.filho.litecommerce.model.ParametroLoja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface ParametroLojaRepository extends CrudRepository<ParametroLoja, Long> {
	// The rest is injected
}

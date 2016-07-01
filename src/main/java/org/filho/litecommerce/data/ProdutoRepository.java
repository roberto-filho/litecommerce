package org.filho.litecommerce.data;

import org.filho.litecommerce.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	// The rest is injected
}

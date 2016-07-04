package org.filho.litecommerce.data;

import org.filho.litecommerce.data.custom.ProdutoRepositoryCustom;
import org.filho.litecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>, JpaSpecificationExecutor<Produto>, JpaRepository<Produto, Long>, ProdutoRepositoryCustom {
	// The rest is injected
}

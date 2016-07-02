package org.filho.litecommerce.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Criada originalmente para ser um service... mas acabou sendo
 * substituída pelo {@code ProdutoRepository}.
 * @author Roberto Filho
 *
 */
@Repository
public class ProdutoService {

	@Autowired
	EntityManager em;
	
	public List<Produto> all() {
		return em.createQuery("Select p from Produto p", Produto.class).getResultList();
	}
}

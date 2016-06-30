package org.filho.litecommerce.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.filho.litecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository {

	@Autowired
	EntityManager em;
	
	public List<Produto> all() {
		return em.createQuery("Select p from Produto", Produto.class).getResultList();
	}
}

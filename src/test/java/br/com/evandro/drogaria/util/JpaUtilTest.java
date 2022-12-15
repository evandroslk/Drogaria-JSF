package br.com.evandro.drogaria.util;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JpaUtilTest {

	@Test
	public void conectar() {
		EntityManager entityManager = JpaUtil.getEntityManager();
		assertNotNull(entityManager);
		entityManager.close();
	}

}

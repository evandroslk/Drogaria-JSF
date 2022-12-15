package br.com.evandro.drogaria.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("drogaria");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}

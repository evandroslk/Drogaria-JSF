package br.com.evandro.drogaria.util;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionImpl;

public class JpaUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("drogaria");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static Connection getConnection() {
		Session session = getEntityManager().unwrap(Session.class);
		return ((SessionImpl) session).connection();
	}

}

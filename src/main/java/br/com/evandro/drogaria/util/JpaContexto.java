package br.com.evandro.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JpaContexto implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		JpaUtil.getEntityManager();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}

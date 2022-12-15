package br.com.evandro.drogaria.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.evandro.drogaria.util.JpaUtil;

public class GenericDAO<E> {

	private Class<E> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(E entidade) {
		EntityManager em = JpaUtil.getEntityManager();

		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();

		em.close();

	}

}

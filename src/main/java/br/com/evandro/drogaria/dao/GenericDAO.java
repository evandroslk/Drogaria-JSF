package br.com.evandro.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

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

	public List<E> listar() {
		EntityManager em = JpaUtil.getEntityManager();
		CriteriaQuery<E> select = em.getCriteriaBuilder().createQuery(classe);
		select.from(classe);
		List<E> lista = em.createQuery(select).getResultList();
		em.close();
		return lista;
	}

	public E buscar(Long codigo) {
		EntityManager em = JpaUtil.getEntityManager();
		E resultado = em.find(classe, codigo);
		em.close();
		return resultado;
	}

	public void excluir(E entidade) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
		em.getTransaction().commit();
		em.close();
	}

	public void merge(E entidade) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
		em.close();
	}

}

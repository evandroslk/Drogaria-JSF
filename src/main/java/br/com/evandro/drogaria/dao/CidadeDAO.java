package br.com.evandro.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.evandro.drogaria.domain.Cidade;
import br.com.evandro.drogaria.util.JpaUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		try {
			EntityManager em = JpaUtil.getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Cidade> query = cb.createQuery(classe);
			Root<Cidade> root = query.from(classe);
			query.select(root).where(cb.equal(root.get("estado").get("codigo"), estadoCodigo));
			query.orderBy(cb.asc(root.get("nome")));
			List<Cidade> resultado = em.createQuery(query).getResultList();
			em.close();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		}
	}

}

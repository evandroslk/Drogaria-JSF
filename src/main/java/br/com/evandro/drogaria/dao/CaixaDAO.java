package br.com.evandro.drogaria.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.evandro.drogaria.domain.Caixa;
import br.com.evandro.drogaria.util.JpaUtil;

public class CaixaDAO extends GenericDAO<Caixa> {
	
	public Caixa buscar(Date dataAbertura) {
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			TypedQuery<Caixa> query = em.createQuery("SELECT c FROM Caixa c WHERE c.dataAbertura = :dataAbertura", 
					Caixa.class);
			query.setParameter("dataAbertura", dataAbertura);
			return query.getSingleResult();
		} catch (RuntimeException e) {
			throw e;
		}
	} 

}

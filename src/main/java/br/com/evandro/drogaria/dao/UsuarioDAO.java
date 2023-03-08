package br.com.evandro.drogaria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.evandro.drogaria.domain.Usuario;
import br.com.evandro.drogaria.util.JpaUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String cpf, String senha) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u JOIN u.pessoa p "
					+ " WHERE p.cpf = :cpf AND u.senha = :senha", 
					Usuario.class);
			query.setParameter("cpf", cpf);
			
			SimpleHash hash = new SimpleHash("md5", senha);
			query.setParameter("senha", hash.toHex());
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw e;
		}
	}

}

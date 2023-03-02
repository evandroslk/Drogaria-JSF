package br.com.evandro.drogaria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.evandro.drogaria.domain.ItemVenda;
import br.com.evandro.drogaria.domain.Produto;
import br.com.evandro.drogaria.domain.Venda;
import br.com.evandro.drogaria.util.JpaUtil;

public class VendaDAO extends GenericDAO<Venda> {

	public void salvar(Venda venda, List<ItemVenda> itensVenda) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			
			em.persist(venda);
			
			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				em.persist(itemVenda);
				
				Produto produto = itemVenda.getProduto();
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				if (quantidade >= 0) {
					produto.setQuantidade(new Short(quantidade + ""));
					em.merge(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
				
			}
			
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			throw e;
		}
	}

}

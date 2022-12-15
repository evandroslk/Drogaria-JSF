package br.com.evandro.drogaria.dao;

import org.junit.Test;

import br.com.evandro.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

}

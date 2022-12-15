package br.com.evandro.drogaria.dao;

import br.com.evandro.drogaria.domain.Estado;

public class Test {
	
	public static void main(String[] args) {
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(3L);
		estadoDAO.excluir(estado);
		
	}

}

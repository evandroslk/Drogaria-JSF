package br.com.evandro.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.evandro.drogaria.dao.EstadoDAO;
import br.com.evandro.drogaria.domain.Estado;

@Path("estado")
public class EstadoService {
	
	@GET
	public String listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.listar("nome");

		Gson gson = new Gson();
		return gson.toJson(estados);
	}

}

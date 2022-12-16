package br.com.evandro.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.evandro.drogaria.dao.FabricanteDAO;
import br.com.evandro.drogaria.domain.Fabricante;

@Path("fabricante")
public class FabricanteService {

	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar();

		Gson gson = new Gson();
		return gson.toJson(fabricantes);
	}

	// http://127.0.0.1:8080/Drogaria/rest/fabricante/{codigo}
	// http://127.0.0.1:8080/Drogaria/rest/fabricante/10
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Long codigo) {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		Gson gson = new Gson();
		return gson.toJson(fabricante);
	}

	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);

		return gson.toJson(fabricante);
	}

	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
		return gson.toJson(fabricante);
	}

	@DELETE
	public String excluir(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricante = fabricanteDAO.buscar(fabricante.getCodigo());
		fabricanteDAO.excluir(fabricante);

		return gson.toJson(fabricante);
	}

}

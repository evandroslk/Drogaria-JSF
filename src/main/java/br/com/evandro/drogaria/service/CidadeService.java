package br.com.evandro.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.evandro.drogaria.dao.CidadeDAO;
import br.com.evandro.drogaria.domain.Cidade;

@Path("cidade")
public class CidadeService {

	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Long estadoCodigo) {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);

		Gson gson = new Gson();
		return gson.toJson(cidades);
	}

}
